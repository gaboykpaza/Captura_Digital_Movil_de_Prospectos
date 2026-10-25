package com.example.capturadigitalmvildeprospectos.data.sync

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.capturadigitalmvildeprospectos.data.local.ProspectoDao
import com.example.capturadigitalmvildeprospectos.data.remote.ProspectoApi
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class SyncProspectosWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workerParams: WorkerParameters,
    private val prospectoDao: ProspectoDao,
    private val prospectoApi: ProspectoApi
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result {
        // 1. Obtiene solo registros pendientes (sincronizado == false)
        val pendientes = prospectoDao.obtenerPendientesSincronizacion()

        if (pendientes.isEmpty()) {
            return Result.success()
        }

        for (prospecto in pendientes) {
            try {
                val response = prospectoApi.enviarProspecto(prospecto)
                if (response.isSuccessful) {
                    // 2. Marca como sincronizado inmediatamente para evitar duplicación
                    prospectoDao.marcarComoSincronizado(prospecto.id)
                }
            } catch (e: Exception) {
                // Si la red falla a mitad del ciclo, reintenta más tarde sin duplicar los ya marcados
                return Result.retry()
            }
        }
        return Result.success()
    }
}
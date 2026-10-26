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
        return try {
            // 1. Consulta todos los registros en Room con sincronizado = false
            val pendientes = prospectoDao.obtenerPendientesSincronizacion()

            // 2. Envia uno a uno a Spring Boot
            for (prospecto in pendientes) {
                val response = prospectoApi.enviarProspecto(prospecto)
                if (response.isSuccessful) {
                    // 3. Si Spring Boot responde 200/201, actualiza en Room a true
                    prospectoDao.marcarComoSincronizado(prospecto.id)
                }
            }
            Result.success()
        } catch (e: Exception) {
            // Si el servidor o la red falla, reintenta después
            Result.retry()
        }
    }
}
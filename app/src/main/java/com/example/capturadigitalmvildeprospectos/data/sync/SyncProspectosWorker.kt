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
        val pendientes = prospectoDao.obtenerPendientesSincronizacion()

        for (prospecto in pendientes) {
            try {
                val response = prospectoApi.enviarProspecto(prospecto)
                if (response.isSuccessful) {
                    prospectoDao.marcarComoSincronizado(prospecto.id)
                }
            } catch (e: Exception) {
                return Result.retry() // Si falla, reintenta cuando vuelva la red
            }
        }
        return Result.success()
    }
}
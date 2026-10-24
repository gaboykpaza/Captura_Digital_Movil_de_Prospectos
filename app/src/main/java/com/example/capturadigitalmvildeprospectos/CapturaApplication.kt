package com.example.capturadigitalmvildeprospectos

import android.app.Application
import androidx.work.*
import com.example.capturadigitalmvildeprospectos.data.sync.SyncProspectosWorker
import dagger.hilt.android.HiltAndroidApp
import java.util.concurrent.TimeUnit

@HiltAndroidApp
class CapturaApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        programarSincronizacionDiferida()
    }

    private fun programarSincronizacionDiferida() {
        // Exige conexión a internet obligatoria para ejecutar el trabajo
        val restricciones = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val syncWorkRequest = PeriodicWorkRequestBuilder<SyncProspectosWorker>(15, TimeUnit.MINUTES)
            .setConstraints(restricciones)
            .build()

        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            "SyncProspectosWork",
            ExistingPeriodicWorkPolicy.KEEP,
            syncWorkRequest
        )
    }
}
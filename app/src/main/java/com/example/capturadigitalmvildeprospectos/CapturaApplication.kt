package com.example.capturadigitalmvildeprospectos

import android.app.Application
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.*
import com.example.capturadigitalmvildeprospectos.data.sync.SyncProspectosWorker
import dagger.hilt.android.HiltAndroidApp
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltAndroidApp
class CapturaApplication : Application(), Configuration.Provider {

    @Inject
    lateinit var workerFactory: HiltWorkerFactory

    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()

    override fun onCreate() {
        super.onCreate()
        programarSincronizacionDiferida()
    }

    private fun programarSincronizacionDiferida() {
        // Exige conexión a internet activa para ejecutar el Worker
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
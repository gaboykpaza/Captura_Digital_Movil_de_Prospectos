package com.example.capturadigitalmvildeprospectos.ui.prospectos

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.*
import com.example.capturadigitalmvildeprospectos.data.local.ProspectoEntity
import com.example.capturadigitalmvildeprospectos.data.repository.ProspectoRepository
import com.example.capturadigitalmvildeprospectos.data.sync.SyncProspectosWorker
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class ListaProspectosViewModel @Inject constructor(
    repository: ProspectoRepository,
    @ApplicationContext private val context: Context
) : ViewModel() {

    init {
        // Al abrir la pantalla de la lista, fuerza a WorkManager a procesar pendientes si hay red
        sincronizarPendientesAhora()
    }

    val prospectos: StateFlow<List<ProspectoEntity>> = repository.obtenerProspectos()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    private fun sincronizarPendientesAhora() {
        val restricciones = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val syncWorkRequest = OneTimeWorkRequestBuilder<SyncProspectosWorker>()
            .setConstraints(restricciones)
            .build()

        WorkManager.getInstance(context).enqueue(syncWorkRequest)
    }
}
package com.example.capturadigitalmvildeprospectos.ui.prospectos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.capturadigitalmvildeprospectos.data.local.ProspectoEntity
import com.example.capturadigitalmvildeprospectos.data.repository.ProspectoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class ListaProspectosViewModel @Inject constructor(
    repository: ProspectoRepository
) : ViewModel() {

    // Flujo reactivo de datos obtenidos desde Room Database
    val prospectos: StateFlow<List<ProspectoEntity>> = repository.obtenerProspectos()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )
}
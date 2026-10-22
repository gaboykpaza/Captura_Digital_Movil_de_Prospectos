package com.example.capturadigitalmvildeprospectos.ejemplos.camara

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class PerfilViewModel @Inject constructor() : ViewModel() {
    private val _imagenUri = MutableStateFlow<String?>(null)
    val imagenUri: StateFlow<String?> = _imagenUri

    fun actualizarImagen(uri: String) {
        _imagenUri.value = uri
    }
}
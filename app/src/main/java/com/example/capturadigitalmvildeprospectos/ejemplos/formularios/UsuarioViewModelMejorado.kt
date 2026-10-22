package com.example.capturadigitalmvildeprospectos.ejemplos.formularios

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

data class FormularioState(
    val nombre: String = "",
    val email: String = "",
    val errorNombre: String? = null,
    val errorEmail: String? = null
)

@HiltViewModel
class UsuarioViewModelMejorado @Inject constructor() : ViewModel() {
    private val _state = MutableStateFlow(FormularioState())
    val state: StateFlow<FormularioState> = _state

    fun onNombreChanged(nombre: String) {
        _state.value = _state.value.copy(nombre = nombre, errorNombre = null)
    }

    fun onEmailChanged(email: String) {
        _state.value = _state.value.copy(email = email, errorEmail = null)
    }

    fun validar(): Boolean {
        var esValido = true
        var errNombre: String? = null
        var errEmail: String? = null

        if (_state.value.nombre.isBlank()) {
            errNombre = "El nombre es requerido"
            esValido = false
        }
        if (!_state.value.email.contains("@")) {
            errEmail = "Correo inválido"
            esValido = false
        }

        _state.value = _state.value.copy(errorNombre = errNombre, errorEmail = errEmail)
        return esValido
    }
}
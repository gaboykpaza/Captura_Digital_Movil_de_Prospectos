package com.example.capturadigitalmvildeprospectos.ui.prospectos

import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.capturadigitalmvildeprospectos.data.repository.ProspectoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistroProspectoViewModel @Inject constructor(
    private val repository: ProspectoRepository
) : ViewModel() {

    private val _state = MutableStateFlow(RegistroProspectoState())
    val state: StateFlow<RegistroProspectoState> = _state.asStateFlow()

    fun onNombreChanged(v: String) { _state.value = _state.value.copy(nombre = v, errorNombre = null) }
    fun onEmailChanged(v: String) { _state.value = _state.value.copy(email = v, errorEmail = null) }
    fun onTelefonoChanged(v: String) { _state.value = _state.value.copy(telefono = v, errorTelefono = null) }
    fun onNivelEducativoChanged(v: String) { _state.value = _state.value.copy(nivelEducativo = v) }
    fun onCursoInteresChanged(v: String) { _state.value = _state.value.copy(cursoInteres = v) }
    fun onModalidadChanged(v: String) { _state.value = _state.value.copy(modalidad = v) }
    fun onCanalContactoChanged(v: String) { _state.value = _state.value.copy(canalContacto = v) }
    fun onObservacionesChanged(v: String) { _state.value = _state.value.copy(observaciones = v) }

    fun guardarProspecto() {
        val s = _state.value
        val emailValido = Patterns.EMAIL_ADDRESS.matcher(s.email).matches()
        val telefonoValido = s.telefono.matches(Regex("^(\\+?56)?9\\d{8}\$")) || s.telefono.length >= 8

        var hayError = false
        if (s.nombre.isBlank()) {
            _state.value = _state.value.copy(errorNombre = "El nombre es obligatorio")
            hayError = true
        }
        if (!emailValido) {
            _state.value = _state.value.copy(errorEmail = "Ingrese un correo válido")
            hayError = true
        }
        if (!telefonoValido) {
            _state.value = _state.value.copy(errorTelefono = "Formato de teléfono inválido (+569XXXXXXXX)")
            hayError = true
        }

        if (!hayError) {
            viewModelScope.launch {
                repository.guardarProspectoLocal(
                    nombre = s.nombre,
                    email = s.email,
                    telefono = s.telefono,
                    nivelEducativo = s.nivelEducativo,
                    cursoInteres = s.cursoInteres,
                    modalidad = s.modalidad,
                    canalContacto = s.canalContacto,
                    observaciones = s.observaciones
                )
                _state.value = _state.value.copy(registroExitoso = true)
            }
        }
    }
}
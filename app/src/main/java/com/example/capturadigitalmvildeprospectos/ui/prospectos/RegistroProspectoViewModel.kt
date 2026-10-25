package com.example.capturadigitalmvildeprospectos.ui.prospectos

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

        // Validación estricta de Correo (Regex estándar de e-mail)
        val emailRegex = Regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}\$")
        val emailValido = emailRegex.matches(s.email.trim())

        // Validación estricta de Teléfono (Chileno: +569XXXXXXXX o 9 dígitos)
        val telefonoRegex = Regex("^(\\+?56)?9\\d{8}\$")
        val telefonoValido = telefonoRegex.matches(s.telefono.trim())

        var hayError = false

        if (s.nombre.isBlank()) {
            _state.value = _state.value.copy(errorNombre = "El nombre es obligatorio")
            hayError = true
        }

        if (!emailValido) {
            _state.value = _state.value.copy(errorEmail = "Ingrese un correo válido (ej: usuario@dominio.cl)")
            hayError = true
        }

        if (!telefonoValido) {
            _state.value = _state.value.copy(errorTelefono = "Teléfono inválido. Use formato +56912345678 o 912345678")
            hayError = true
        }

        // Bloqueo de guardado si hay cualquier error de validación
        if (hayError) return

        viewModelScope.launch {
            repository.guardarProspectoLocal(
                nombre = s.nombre.trim(),
                email = s.email.trim(),
                telefono = s.telefono.trim(),
                nivelEducativo = s.nivelEducativo,
                cursoInteres = s.cursoInteres,
                modalidad = s.modalidad,
                canalContacto = s.canalContacto,
                observaciones = s.observaciones
            )
            _state.value = _state.value.copy(
                registroExitoso = true,
                nombre = "",
                email = "",
                telefono = "",
                observaciones = ""
            )
        }
    }
}
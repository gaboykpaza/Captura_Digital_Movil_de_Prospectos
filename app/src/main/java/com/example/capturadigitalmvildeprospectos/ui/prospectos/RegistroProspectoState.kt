package com.example.capturadigitalmvildeprospectos.ui.prospectos

data class RegistroProspectoState(
    val nombre: String = "",
    val email: String = "",
    val telefono: String = "",
    val nivelEducativo: String = "Educación Media",
    val cursoInteres: String = "Desarrollo Móvil Android",
    val modalidad: String = "Presencial",
    val canalContacto: String = "WhatsApp",
    val observaciones: String = "",
    val errorNombre: String? = null,
    val errorEmail: String? = null,
    val errorTelefono: String? = null,
    val registroExitoso: Boolean = false
)
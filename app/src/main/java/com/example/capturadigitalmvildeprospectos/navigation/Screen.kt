package com.example.capturadigitalmvildeprospectos.navigation

sealed class Screen(val route: String) {
    object Registro : Screen("registro")
    object ListaProspectos : Screen("lista_prospectos")
    object PerfilCamara : Screen("perfil_camara")
}
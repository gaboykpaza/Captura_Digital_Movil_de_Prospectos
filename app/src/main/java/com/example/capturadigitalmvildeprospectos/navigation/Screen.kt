package com.example.capturadigitalmvildeprospectos.navigation

sealed class Screen(val route: String) {
    object BuscarPapers : Screen("buscar_papers")
    object FavoritosPapers : Screen("favoritos_papers")
    object Adaptabilidad : Screen("adaptabilidad")
    object Camara : Screen("camara")
    object Formulario : Screen("formulario")
    object ResumenFormulario : Screen("resumen_formulario/{nombre}/{email}") {
        fun createRoute(nombre: String, email: String) = "resumen_formulario/$nombre/$email"
    }
    object NavegacionEjemplo : Screen("navegacion_ejemplo")
}

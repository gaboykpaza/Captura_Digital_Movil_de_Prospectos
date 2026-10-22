package com.example.capturadigitalmvildeprospectos.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.capturadigitalmvildeprospectos.ejemplos.adaptabilidad.HomeScreenAdaptativa
import com.example.capturadigitalmvildeprospectos.ejemplos.camara.PerfilScreen
import com.example.capturadigitalmvildeprospectos.ejemplos.formularios.RegistroScreenMejorado
import com.example.capturadigitalmvildeprospectos.ejemplos.formularios.ResumenScreen
import com.example.capturadigitalmvildeprospectos.ejemplos.navegacion.PantallasMejoradas
import com.example.capturadigitalmvildeprospectos.papers.ui.BuscarPapersScreen
import com.example.capturadigitalmvildeprospectos.papers.ui.FavoritosScreen

@Composable
fun AppNavigationMejorada() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.BuscarPapers.route
    ) {
        composable(Screen.BuscarPapers.route) {
            BuscarPapersScreen(
                onNavigateToFavoritos = { navController.navigate(Screen.FavoritosPapers.route) },
                onNavigateToEjemplos = { navController.navigate(Screen.Adaptabilidad.route) }
            )
        }

        composable(Screen.FavoritosPapers.route) {
            FavoritosScreen(onBack = { navController.popBackStack() })
        }

        composable(Screen.Adaptabilidad.route) {
            HomeScreenAdaptativa(
                onNavigateToCamara = { navController.navigate(Screen.Camara.route) },
                onNavigateToFormulario = { navController.navigate(Screen.Formulario.route) },
                onNavigateToNavegacion = { navController.navigate(Screen.NavegacionEjemplo.route) }
            )
        }

        composable(Screen.Camara.route) {
            PerfilScreen(onBack = { navController.popBackStack() })
        }

        composable(Screen.Formulario.route) {
            RegistroScreenMejorado(
                onSuccess = { nombre, email ->
                    navController.navigate(Screen.ResumenFormulario.createRoute(nombre, email))
                }
            )
        }

        composable(
            route = Screen.ResumenFormulario.route,
            arguments = listOf(
                navArgument("nombre") { type = NavType.StringType },
                navArgument("email") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val nombre = backStackEntry.arguments?.getString("nombre") ?: ""
            val email = backStackEntry.arguments?.getString("email") ?: ""
            ResumenScreen(
                nombre = nombre,
                email = email,
                onBack = { navController.popBackStack() }
            )
        }

        composable(Screen.NavegacionEjemplo.route) {
            PantallasMejoradas(onBack = { navController.popBackStack() })
        }
    }
}
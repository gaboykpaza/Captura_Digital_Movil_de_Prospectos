package com.example.capturadigitalmvildeprospectos.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.capturadigitalmvildeprospectos.ui.PerfilScreen
import com.example.capturadigitalmvildeprospectos.ui.prospectos.ListaProspectosScreen
import com.example.capturadigitalmvildeprospectos.ui.prospectos.ListaProspectosViewModel
import com.example.capturadigitalmvildeprospectos.ui.prospectos.RegistroProspectoScreen

@Composable
fun AppNavigation(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Registro.route
    ) {
        // Pantalla 1: Formulario de Registro de Prospectos
        composable(Screen.Registro.route) {
            RegistroProspectoScreen(
                onIrALista = { navController.navigate(Screen.ListaProspectos.route) },
                onIrACamara = { navController.navigate(Screen.PerfilCamara.route) }
            )
        }

        // Pantalla 2: Lista de Prospectos Guardados (Room)
        composable(Screen.ListaProspectos.route) {
            val viewModel: ListaProspectosViewModel = hiltViewModel()
            val prospectos by viewModel.prospectos.collectAsState()

            ListaProspectosScreen(
                prospectos = prospectos,
                onVolver = { navController.popBackStack() },
                onSincronizar = { viewModel.sincronizarManualmente() }
            )
        }

        // Pantalla 3: Captura de Foto
        composable(Screen.PerfilCamara.route) {
            PerfilScreen(
                onVolver = { navController.popBackStack() }
            )
        }
    }
}
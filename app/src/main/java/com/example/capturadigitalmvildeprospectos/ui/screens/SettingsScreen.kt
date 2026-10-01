package com.example.capturadigitalmvildeprospectos.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.capturadigitalmvildeprospectos.navigation.Screen
import com.example.capturadigitalmvildeprospectos.viewmodels.MainViewModel

@Composable
fun SettingsScreen(
    navController: NavController,
    viewModel: MainViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Pantalla de Configuración (Settings)")
        Spacer(modifier = Modifier.height(24.dp))
        Button(
            onClick = { viewModel.navigateTo(Screen.Home) }
        ) {
            Text(text = "Volver al Inicio")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { viewModel.navigateTo(Screen.Profile) }
        ) {
            Text(text = "Ir a Perfil")
        }
    }
}
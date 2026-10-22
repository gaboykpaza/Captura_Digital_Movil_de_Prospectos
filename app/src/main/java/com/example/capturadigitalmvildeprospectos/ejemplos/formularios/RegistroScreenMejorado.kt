package com.example.capturadigitalmvildeprospectos.ejemplos.formularios

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun RegistroScreenMejorado(
    onSuccess: (String, String) -> Unit,
    viewModel: UsuarioViewModelMejorado = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Registro de Prospecto", style = MaterialTheme.typography.titleLarge)

        OutlinedTextField(
            value = state.nombre,
            onValueChange = viewModel::onNombreChanged,
            label = { Text("Nombre") },
            isError = state.errorNombre != null,
            modifier = Modifier.fillMaxWidth()
        )

        // Animación de aparición/desaparición suave para el error de Nombre
        AnimatedVisibility(
            visible = state.errorNombre != null,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Text(
                text = state.errorNombre ?: "",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }

        OutlinedTextField(
            value = state.email,
            onValueChange = viewModel::onEmailChanged,
            label = { Text("Email") },
            isError = state.errorEmail != null,
            modifier = Modifier.fillMaxWidth()
        )

        // Animación de aparición/desaparición suave para el error de Email
        AnimatedVisibility(
            visible = state.errorEmail != null,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Text(
                text = state.errorEmail ?: "",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }

        Button(
            onClick = { if (viewModel.validar()) onSuccess(state.nombre, state.email) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Registrar")
        }
    }
}
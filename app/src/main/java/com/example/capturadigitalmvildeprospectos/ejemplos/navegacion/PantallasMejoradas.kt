package com.example.capturadigitalmvildeprospectos.ejemplos.navegacion

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun PantallasMejoradas(onBack: () -> Unit, viewModel: MainViewModelMejorado = hiltViewModel()) {
    val conteo by viewModel.conteo.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Conteo: $conteo")
        Button(onClick = { viewModel.incrementar() }) { Text("Incrementar") }
        Button(onClick = onBack) { Text("Volver") }
    }
}
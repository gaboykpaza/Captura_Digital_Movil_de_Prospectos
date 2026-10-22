package com.example.capturadigitalmvildeprospectos.ejemplos.adaptabilidad

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.capturadigitalmvildeprospectos.ui.utils.WindowSizeClass
import com.example.capturadigitalmvildeprospectos.ui.utils.rememberWindowSizeClass

@Composable
fun HomeScreenAdaptativa(
    onNavigateToCamara: () -> Unit,
    onNavigateToFormulario: () -> Unit,
    onNavigateToNavegacion: () -> Unit
) {
    val windowSize = rememberWindowSizeClass()

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Modo de pantalla: $windowSize")
        Button(onClick = onNavigateToCamara, modifier = Modifier.fillMaxWidth()) { Text("Ir a Cámara") }
        Button(onClick = onNavigateToFormulario, modifier = Modifier.fillMaxWidth()) { Text("Ir a Formulario") }
        Button(onClick = onNavigateToNavegacion, modifier = Modifier.fillMaxWidth()) { Text("Ir a Navegación") }
    }
}
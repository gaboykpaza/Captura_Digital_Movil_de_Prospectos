package com.example.capturadigitalmvildeprospectos.ejemplos.formularios

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ResumenScreen(nombre: String, email: String, onBack: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Prospecto Guardado")
        Text("Nombre: $nombre")
        Text("Email: $email")
        Button(onClick = onBack) { Text("Volver") }
    }
}
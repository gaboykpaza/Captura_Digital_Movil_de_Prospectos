package com.example.capturadigitalmvildeprospectos.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SelectorVersion(
    opciones: List<String>,
    seleccionado: String,
    onSeleccionCambiada: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier) {
        opciones.forEach { opcion ->
            FilterChip(
                selected = opcion == seleccionado,
                onClick = { onSeleccionCambiada(opcion) },
                label = { Text(opcion) }
            )
        }
    }
}
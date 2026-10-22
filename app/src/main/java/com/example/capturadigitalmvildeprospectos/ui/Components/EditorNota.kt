package com.example.capturadigitalmvildeprospectos.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun EditorNota(
    nota: String,
    onNotaChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = nota,
        onValueChange = onNotaChange,
        label = { Text("Nota personal") },
        modifier = modifier.fillMaxWidth()
    )
}
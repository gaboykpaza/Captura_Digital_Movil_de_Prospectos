package com.example.capturadigitalmvildeprospectos.ejemplos.camara

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun CamaraEntry(onTomarFoto: () -> Unit) {
    Button(onClick = onTomarFoto) {
        Text("Tomar Foto")
    }
}
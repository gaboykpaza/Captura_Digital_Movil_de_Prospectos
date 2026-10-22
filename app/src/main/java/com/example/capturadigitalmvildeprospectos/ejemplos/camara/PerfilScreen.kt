package com.example.capturadigitalmvildeprospectos.ejemplos.camara

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PerfilScreen(onBack: () -> Unit, viewModel: PerfilViewModel = hiltViewModel()) {
    val imagenUri by viewModel.imagenUri.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Cámara de Perfil") },
                navigationIcon = {
                    IconButton(onClick = onBack) { Icon(Icons.Default.ArrowBack, contentDescription = "Volver") }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(padding).padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            ImagenInteligente(imagenUri = imagenUri)
            CamaraEntry(onTomarFoto = { viewModel.actualizarImagen("foto_ejemplo.jpg") })
        }
    }
}
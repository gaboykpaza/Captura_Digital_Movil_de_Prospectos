package com.example.capturadigitalmvildeprospectos.papers.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.capturadigitalmvildeprospectos.papers.viewmodel.FavoritosViewModel
import com.example.capturadigitalmvildeprospectos.ui.components.TarjetaPaper

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritosScreen(onBack: () -> Unit, viewModel: FavoritosViewModel = hiltViewModel()) {
    val favoritos by viewModel.favoritos.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Papers Favoritos") },
                navigationIcon = {
                    IconButton(onClick = onBack) { Icon(Icons.Default.ArrowBack, contentDescription = "Volver") }
                }
            )
        }
    ) { padding ->
        Column(modifier = Modifier.fillMaxSize().padding(padding).padding(16.dp)) {
            if (favoritos.isEmpty()) {
                Text("No tienes ningún paper guardado en favoritos.")
            } else {
                LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(favoritos) { paper ->
                        TarjetaPaper(paper = paper, onToggleFavorito = { viewModel.eliminarFavorito(it) })
                    }
                }
            }
        }
    }
}
package com.example.capturadigitalmvildeprospectos.papers.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.capturadigitalmvildeprospectos.papers.viewmodel.BuscarPapersViewModel
import com.example.capturadigitalmvildeprospectos.papers.viewmodel.BuscarUiState
import com.example.capturadigitalmvildeprospectos.ui.components.TarjetaPaper

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BuscarPapersScreen(
    onNavigateToFavoritos: () -> Unit,
    onNavigateToEjemplos: () -> Unit,
    viewModel: BuscarPapersViewModel = hiltViewModel()
) {
    var query by remember { mutableStateOf("") }
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Buscador de Papers") },
                actions = {
                    IconButton(onClick = onNavigateToEjemplos) { Icon(Icons.Default.List, contentDescription = "Ejemplos") }
                    IconButton(onClick = onNavigateToFavoritos) { Icon(Icons.Default.Favorite, contentDescription = "Favoritos") }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(padding).padding(16.dp)
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(
                    value = query,
                    onValueChange = { query = it },
                    label = { Text("Buscar paper...") },
                    modifier = Modifier.weight(1f)
                )
                Button(onClick = { viewModel.buscar(query) }) { Text("Buscar") }
            }

            Spacer(modifier = Modifier.height(16.dp))

            when (val state = uiState) {
                is BuscarUiState.Idle -> Text("Ingresa una palabra clave para buscar.")
                is BuscarUiState.Loading -> CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
                is BuscarUiState.Error -> Text("Error: ${state.message}", color = MaterialTheme.colorScheme.error)
                is BuscarUiState.Success -> {
                    LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        items(state.papers) { paper ->
                            TarjetaPaper(paper = paper, onToggleFavorito = { viewModel.toggleFavorito(it) })
                        }
                    }
                }
            }
        }
    }
}
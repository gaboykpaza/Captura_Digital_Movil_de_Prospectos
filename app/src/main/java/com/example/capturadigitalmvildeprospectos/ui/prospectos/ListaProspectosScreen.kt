package com.example.capturadigitalmvildeprospectos.ui.prospectos

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.capturadigitalmvildeprospectos.data.local.ProspectoEntity

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListaProspectosScreen(
    prospectos: List<ProspectoEntity>,
    onVolver: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Prospectos Registrados") },
                navigationIcon = {
                    IconButton(onClick = onVolver) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Volver"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            if (prospectos.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("No hay prospectos guardados aún en Room.")
                }
            } else {
                LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(prospectos) { prospecto ->
                        Card(modifier = Modifier.fillMaxWidth()) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                Row(
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    modifier = Modifier.fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = prospecto.nombre,
                                        style = MaterialTheme.typography.titleMedium,
                                        modifier = Modifier.weight(1f) // <-- Agregado para que tome el espacio sobrante
                                    )

                                    Spacer(modifier = Modifier.width(8.dp))

                                    // Badge visual de estado
                                    AssistChip(
                                        onClick = { },
                                        label = { Text(if (prospecto.sincronizado) "Sincronizado" else "Pendiente Offline") },
                                        colors = AssistChipDefaults.assistChipColors(
                                            containerColor = if (prospecto.sincronizado) Color(0xFFE8F5E9) else Color(0xFFFFF3E0)
                                        )
                                    )
                                }
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(text = "Email: ${prospecto.email}")
                                Text(text = "Teléfono: ${prospecto.telefono}")
                                Text(text = "Curso: ${prospecto.cursoInteres} (${prospecto.modalidad})")
                                Text(text = "Contacto vía: ${prospecto.canalContacto}")
                            }
                        }
                    }
                }
            }
        }
    }
}
package com.example.capturadigitalmvildeprospectos.ui.prospectos

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistroProspectoScreen(
    onIrALista: () -> Unit,
    onIrACamara: () -> Unit,
    viewModel: RegistroProspectoViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    val scrollState = rememberScrollState()

    // Opciones para menús desplegables
    val opcionesNivel = listOf("Educación Media", "Técnico Nivel Superior", "Profesional / Universitario", "Postgrado")
    val opcionesCursos = listOf("Desarrollo Móvil Android", "Spring Boot Microservicios", "Data Science & AI", "Ciberseguridad")
    val opcionesModalidad = listOf("Presencial", "Online")
    val opcionesCanal = listOf("WhatsApp", "Correo", "Teléfono")

    // Estados de apertura de menús desplegables
    var expandedNivel by remember { mutableStateOf(false) }
    var expandedCurso by remember { mutableStateOf(false) }
    var expandedModalidad by remember { mutableStateOf(false) }
    var expandedCanal by remember { mutableStateOf(false) }

    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(state.registroExitoso) {
        if (state.registroExitoso) {
            snackbarHostState.showSnackbar("Prospecto guardado exitosamente en Room")
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "Captura de Prospectos - THERIONLABS",
                style = MaterialTheme.typography.titleLarge
            )

            // 1. Campo Nombre
            OutlinedTextField(
                value = state.nombre,
                onValueChange = viewModel::onNombreChanged,
                label = { Text("Nombre Completo *") },
                isError = state.errorNombre != null,
                modifier = Modifier.fillMaxWidth()
            )
            AnimatedVisibility(
                visible = state.errorNombre != null,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                Text(
                    text = state.errorNombre ?: "",
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            // 2. Campo Correo
            OutlinedTextField(
                value = state.email,
                onValueChange = viewModel::onEmailChanged,
                label = { Text("Correo Electrónico *") },
                isError = state.errorEmail != null,
                modifier = Modifier.fillMaxWidth()
            )
            AnimatedVisibility(
                visible = state.errorEmail != null,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                Text(
                    text = state.errorEmail ?: "",
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            // 3. Campo Teléfono
            OutlinedTextField(
                value = state.telefono,
                onValueChange = viewModel::onTelefonoChanged,
                label = { Text("Teléfono (+569XXXXXXXX) *") },
                isError = state.errorTelefono != null,
                modifier = Modifier.fillMaxWidth()
            )
            AnimatedVisibility(
                visible = state.errorTelefono != null,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                Text(
                    text = state.errorTelefono ?: "",
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            // 4. Desplegable Nivel Educativo
            ExposedDropdownMenuBox(
                expanded = expandedNivel,
                onExpandedChange = { expandedNivel = !expandedNivel }
            ) {
                OutlinedTextField(
                    value = state.nivelEducativo,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Nivel Educativo") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedNivel) },
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth()
                )
                ExposedDropdownMenu(
                    expanded = expandedNivel,
                    onDismissRequest = { expandedNivel = false }
                ) {
                    opcionesNivel.forEach { opcion ->
                        DropdownMenuItem(
                            text = { Text(opcion) },
                            onClick = {
                                viewModel.onNivelEducativoChanged(opcion)
                                expandedNivel = false
                            }
                        )
                    }
                }
            }

            // 5. Desplegable Oferta Académica
            ExposedDropdownMenuBox(
                expanded = expandedCurso,
                onExpandedChange = { expandedCurso = !expandedCurso }
            ) {
                OutlinedTextField(
                    value = state.cursoInteres,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Curso / Programa de Interés") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedCurso) },
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth()
                )
                ExposedDropdownMenu(
                    expanded = expandedCurso,
                    onDismissRequest = { expandedCurso = false }
                ) {
                    opcionesCursos.forEach { opcion ->
                        DropdownMenuItem(
                            text = { Text(opcion) },
                            onClick = {
                                viewModel.onCursoInteresChanged(opcion)
                                expandedCurso = false
                            }
                        )
                    }
                }
            }

            // 6. Desplegable Modalidad
            ExposedDropdownMenuBox(
                expanded = expandedModalidad,
                onExpandedChange = { expandedModalidad = !expandedModalidad }
            ) {
                OutlinedTextField(
                    value = state.modalidad,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Modalidad Preferida") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedModalidad) },
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth()
                )
                ExposedDropdownMenu(
                    expanded = expandedModalidad,
                    onDismissRequest = { expandedModalidad = false }
                ) {
                    opcionesModalidad.forEach { opcion ->
                        DropdownMenuItem(
                            text = { Text(opcion) },
                            onClick = {
                                viewModel.onModalidadChanged(opcion)
                                expandedModalidad = false
                            }
                        )
                    }
                }
            }

            // 7. Desplegable Canal de Contacto
            ExposedDropdownMenuBox(
                expanded = expandedCanal,
                onExpandedChange = { expandedCanal = !expandedCanal }
            ) {
                OutlinedTextField(
                    value = state.canalContacto,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Canal Preferido de Contacto") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedCanal) },
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth()
                )
                ExposedDropdownMenu(
                    expanded = expandedCanal,
                    onDismissRequest = { expandedCanal = false }
                ) {
                    opcionesCanal.forEach { opcion ->
                        DropdownMenuItem(
                            text = { Text(opcion) },
                            onClick = {
                                viewModel.onCanalContactoChanged(opcion)
                                expandedCanal = false
                            }
                        )
                    }
                }
            }

            // 8. Observaciones
            OutlinedTextField(
                value = state.observaciones,
                onValueChange = viewModel::onObservacionesChanged,
                label = { Text("Observaciones") },
                modifier = Modifier.fillMaxWidth(),
                maxLines = 3
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Botón Guardar
            Button(
                onClick = { viewModel.guardarProspecto() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Guardar Prospecto")
            }

            // Navegación
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedButton(
                    onClick = onIrALista,
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Ver Registros")
                }

                OutlinedButton(
                    onClick = onIrACamara,
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Tomar Foto")
                }
            }
        }
    }
}
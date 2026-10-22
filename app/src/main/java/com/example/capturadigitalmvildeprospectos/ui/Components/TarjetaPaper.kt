package com.example.capturadigitalmvildeprospectos.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.capturadigitalmvildeprospectos.papers.model.Paper

@Composable
fun TarjetaPaper(
    paper: Paper,
    onToggleFavorito: (Paper) -> Unit,
    modifier: Modifier = Modifier
) {
    // Animación de transición suave para el color del icono de favorito
    val iconoColor by animateColorAsState(
        targetValue = if (paper.esFavorito) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline,
        label = "ColorFavoritoAnimation"
    )

    Card(
        modifier = modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = paper.titulo,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.weight(1f)
                )
                IconButton(onClick = { onToggleFavorito(paper) }) {
                    Icon(
                        imageVector = if (paper.esFavorito) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = "Favorito",
                        tint = iconoColor
                    )
                }
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "Año: ${paper.anioPublicacion}", style = MaterialTheme.typography.bodySmall)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = paper.resumen, style = MaterialTheme.typography.bodyMedium, maxLines = 3)
        }
    }
}
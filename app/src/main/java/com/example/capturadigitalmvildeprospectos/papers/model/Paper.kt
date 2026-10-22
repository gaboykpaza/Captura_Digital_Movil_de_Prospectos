package com.example.capturadigitalmvildeprospectos.papers.model

data class Paper(
    val id: String,
    val titulo: String,
    val resumen: String,
    val autores: List<String>,
    val anioPublicacion: Int,
    val esFavorito: Boolean = false
)
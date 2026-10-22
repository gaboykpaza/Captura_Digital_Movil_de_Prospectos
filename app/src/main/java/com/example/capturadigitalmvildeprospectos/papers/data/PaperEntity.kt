package com.example.capturadigitalmvildeprospectos.papers.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "papers_favoritos")
data class PaperEntity(
    @PrimaryKey val id: String,
    val titulo: String,
    val resumen: String,
    val autores: String,
    val anioPublicacion: Int
)
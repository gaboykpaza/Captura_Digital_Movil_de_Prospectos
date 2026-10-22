package com.example.capturadigitalmvildeprospectos.papers.data

import com.example.capturadigitalmvildeprospectos.papers.model.Paper

fun OpenApiPaperDto.toPaper(esFavorito: Boolean = false): Paper {
    return Paper(
        id = this.id,
        titulo = this.title ?: "Sin título",
        resumen = this.abstractText ?: "Sin resumen.",
        autores = listOf("Autor Desconocido"),
        anioPublicacion = this.year ?: 2024,
        esFavorito = esFavorito
    )
}

fun PaperEntity.toPaper(): Paper {
    return Paper(
        id = this.id,
        titulo = this.titulo,
        resumen = this.resumen,
        autores = this.autores.split(", "),
        anioPublicacion = this.anioPublicacion,
        esFavorito = true
    )
}

fun Paper.toEntity(): PaperEntity {
    return PaperEntity(
        id = this.id,
        titulo = this.titulo,
        resumen = this.resumen,
        autores = this.autores.joinToString(", "),
        anioPublicacion = this.anioPublicacion
    )
}
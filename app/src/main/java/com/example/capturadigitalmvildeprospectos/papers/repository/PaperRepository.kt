package com.example.capturadigitalmvildeprospectos.papers.repository

import com.example.capturadigitalmvildeprospectos.papers.data.*
import com.example.capturadigitalmvildeprospectos.papers.model.Paper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PaperRepository @Inject constructor(
    private val api: OpenApi,
    private val dao: PaperDao
) {
    suspend fun buscarPapers(query: String): List<Paper> {
        val response = api.buscarPapers(query)
        val list = response.results ?: emptyList()
        return list.map { dto ->
            val esFav = dao.esFavorito(dto.id)
            dto.toPaper(esFavorito = esFav)
        }
    }

    fun getFavoritos(): Flow<List<Paper>> {
        return dao.getFavoritos().map { entities -> entities.map { it.toPaper() } }
    }

    suspend fun toggleFavorito(paper: Paper) {
        if (paper.esFavorito) {
            dao.eliminarFavorito(paper.toEntity())
        } else {
            dao.guardarFavorito(paper.toEntity())
        }
    }
}
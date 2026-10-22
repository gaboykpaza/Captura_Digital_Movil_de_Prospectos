package com.example.capturadigitalmvildeprospectos.papers.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface PaperDao {
    @Query("SELECT * FROM papers_favoritos")
    fun getFavoritos(): Flow<List<PaperEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun guardarFavorito(paper: PaperEntity)

    @Delete
    suspend fun eliminarFavorito(paper: PaperEntity)

    @Query("SELECT EXISTS(SELECT 1 FROM papers_favoritos WHERE id = :id)")
    suspend fun esFavorito(id: String): Boolean
}
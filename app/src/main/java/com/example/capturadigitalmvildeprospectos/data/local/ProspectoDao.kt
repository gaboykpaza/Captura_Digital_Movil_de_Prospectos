package com.example.capturadigitalmvildeprospectos.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ProspectoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarProspecto(prospecto: ProspectoEntity): Long

    @Query("SELECT * FROM prospectos ORDER BY fechaCaptura DESC")
    fun obtenerTodosLosProspectos(): Flow<List<ProspectoEntity>>

    @Query("SELECT * FROM prospectos WHERE sincronizado = 0")
    suspend fun obtenerPendientesSincronizacion(): List<ProspectoEntity>

    @Query("UPDATE prospectos SET sincronizado = 1 WHERE id = :id")
    suspend fun marcarComoSincronizado(id: Long)
}
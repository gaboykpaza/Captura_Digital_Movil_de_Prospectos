package com.example.capturadigitalmvildeprospectos.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "prospectos")
data class ProspectoEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val nombre: String,
    val email: String,
    val telefono: String,
    val nivelEducativo: String,
    val cursoInteres: String,
    val modalidad: String,         // "Online" o "Presencial"
    val canalContacto: String,     // "Correo", "Teléfono", "WhatsApp"
    val observaciones: String = "",
    val fechaCaptura: Long = System.currentTimeMillis(),
    val sincronizado: Boolean = false // Estado de sincronización para WorkManager
)
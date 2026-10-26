package com.example.capturadigitalmvildeprospectos.data.repository

import com.example.capturadigitalmvildeprospectos.data.local.ProspectoDao
import com.example.capturadigitalmvildeprospectos.data.local.ProspectoEntity
import com.example.capturadigitalmvildeprospectos.data.remote.ProspectoApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProspectoRepository @Inject constructor(
    private val prospectoDao: ProspectoDao,
    private val prospectoApi: ProspectoApi
) {
    fun obtenerProspectos(): Flow<List<ProspectoEntity>> = prospectoDao.obtenerTodosLosProspectos()

    suspend fun guardarProspectoLocal(
        nombre: String, email: String, telefono: String,
        nivelEducativo: String, cursoInteres: String,
        modalidad: String, canalContacto: String, observaciones: String
    ) {
        val nuevoProspecto = ProspectoEntity(
            nombre = nombre, email = email, telefono = telefono,
            nivelEducativo = nivelEducativo, cursoInteres = cursoInteres,
            modalidad = modalidad, canalContacto = canalContacto,
            observaciones = observaciones,
            sincronizado = false
        )

        // 1. Guardar localmente en Room (devuelve un id de tipo Long)
        val idGenerado = prospectoDao.insertarProspecto(nuevoProspecto)

        // 2. Intentar envío inmediato a Spring Boot
        try {
            val response = prospectoApi.enviarProspecto(nuevoProspecto.copy(id = idGenerado))
            if (response.isSuccessful) {
                prospectoDao.marcarComoSincronizado(idGenerado)
            }
        } catch (e: Exception) {
            // Permanece como sincronizado = false en Room
        }
    }
}
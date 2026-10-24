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
            observaciones = observaciones, sincronizado = false
        )

        // Guardado local obligatorio en Room
        val idGenerado = prospectoDao.insertarProspecto(nuevoProspecto)

        // Intento de envío inmediato vía Retrofit
        try {
            val response = prospectoApi.enviarProspecto(nuevoProspecto.copy(id = idGenerado))
            if (response.isSuccessful) {
                prospectoDao.marcarComoSincronizado(idGenerado)
            }
        } catch (e: Exception) {
            // Sin conexión o fallo en Spring Boot: permanece en Room con sincronizado = false
        }
    }
}
package com.example.capturadigitalmvildeprospectos.data.repository

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.example.capturadigitalmvildeprospectos.data.local.ProspectoDao
import com.example.capturadigitalmvildeprospectos.data.local.ProspectoEntity
import com.example.capturadigitalmvildeprospectos.data.remote.ProspectoApi
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProspectoRepository @Inject constructor(
    private val prospectoDao: ProspectoDao,
    private val prospectoApi: ProspectoApi,
    @ApplicationContext private val context: Context
) {
    fun obtenerProspectos(): Flow<List<ProspectoEntity>> = prospectoDao.obtenerTodosLosProspectos()

    // Comprueba si el dispositivo tiene red/internet activa
    private fun tieneConexionInternet(): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
        return capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }

    suspend fun guardarProspectoLocal(
        nombre: String, email: String, telefono: String,
        nivelEducativo: String, cursoInteres: String,
        modalidad: String, canalContacto: String, observaciones: String
    ) {
        val estaConectado = tieneConexionInternet()

        // Si hay internet, nace como sincronizado (true). Si está en Modo Avión, nace como pendiente (false).
        val nuevoProspecto = ProspectoEntity(
            nombre = nombre, email = email, telefono = telefono,
            nivelEducativo = nivelEducativo, cursoInteres = cursoInteres,
            modalidad = modalidad, canalContacto = canalContacto,
            observaciones = observaciones,
            sincronizado = estaConectado
        )

        // 1. Guardar localmente en Room
        val idGenerado = prospectoDao.insertarProspecto(nuevoProspecto)

        // 2. Intentar envío REST a Spring Boot si hay conexión
        if (estaConectado) {
            try {
                prospectoApi.enviarProspecto(nuevoProspecto.copy(id = idGenerado))
            } catch (e: Exception) {
                // Si la API no responde, el registro se conserva en Room
            }
        }
    }
}
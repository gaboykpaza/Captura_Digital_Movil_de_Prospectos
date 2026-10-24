package com.example.capturadigitalmvildeprospectos.data.remote

import com.example.capturadigitalmvildeprospectos.data.local.ProspectoEntity
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ProspectoApi {
    @POST("api/prospectos")
    suspend fun enviarProspecto(@Body prospecto: ProspectoEntity): Response<Unit>
}
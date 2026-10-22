package com.example.capturadigitalmvildeprospectos.papers.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object OpenApiClient {
    private const val BASE_URL = "https://api.openalex.org/"

    val service: OpenApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(OpenApi::class.java)
    }
}
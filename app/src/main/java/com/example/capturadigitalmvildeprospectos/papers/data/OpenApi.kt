package com.example.capturadigitalmvildeprospectos.papers.data

import com.google.gson.annotations.SerializedName
import retrofit2.http.GET
import retrofit2.http.Query

data class OpenApiResponse(
    @SerializedName("results") val results: List<OpenApiPaperDto>?
)

data class OpenApiPaperDto(
    @SerializedName("id") val id: String,
    @SerializedName("title") val title: String?,
    @SerializedName("abstract") val abstractText: String?,
    @SerializedName("publication_year") val year: Int?
)

interface OpenApi {
    @GET("works")
    suspend fun buscarPapers(@Query("search") query: String): OpenApiResponse
}
package com.example.capturadigitalmvildeprospectos.di

import com.example.capturadigitalmvildeprospectos.data.remote.ProspectoApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    // 10.0.2.2 es la IP por defecto que usa el emulador de Android para comunicarse con tu localhost (Spring Boot)
    private const val BASE_URL = "http://10.0.2.2:8080/"

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideProspectoApi(retrofit: Retrofit): ProspectoApi {
        return retrofit.create(ProspectoApi::class.java)
    }
}
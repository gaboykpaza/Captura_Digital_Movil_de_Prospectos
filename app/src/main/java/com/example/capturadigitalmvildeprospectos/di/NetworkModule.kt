package com.example.capturadigitalmvildeprospectos.di

import android.os.Build
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

    // Detecta si la app está corriendo en un emulador de Android
    private val esEmulador: Boolean
        get() = Build.FINGERPRINT.startsWith("generic")
                || Build.FINGERPRINT.startsWith("unknown")
                || Build.MODEL.contains("google_sdk")
                || Build.MODEL.contains("Emulator")
                || Build.MODEL.contains("Android SDK built for x86")
                || Build.MANUFACTURER.contains("Genymotion")

    // Asigna la IP según el dispositivo en uso
    private val BASE_URL = if (esEmulador) {
        "http://10.0.2.2:8080/"        // URL para el Emulador
    } else {
        "http://10.199.161.65:8080/"   // URL para tu Teléfono Físico
    }

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
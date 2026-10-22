package com.example.capturadigitalmvildeprospectos.di

import android.content.Context
import androidx.room.Room
import com.example.capturadigitalmvildeprospectos.papers.data.OpenApi
import com.example.capturadigitalmvildeprospectos.papers.data.OpenApiClient
import com.example.capturadigitalmvildeprospectos.papers.data.PaperDao
import com.example.capturadigitalmvildeprospectos.papers.data.PaperDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): PaperDatabase {
        return Room.databaseBuilder(
            context,
            PaperDatabase::class.java,
            "papers_db"
        ).build()
    }

    @Provides
    fun providePaperDao(database: PaperDatabase): PaperDao {
        return database.paperDao()
    }

    @Provides
    @Singleton
    fun provideOpenApi(): OpenApi {
        return OpenApiClient.service
    }
}
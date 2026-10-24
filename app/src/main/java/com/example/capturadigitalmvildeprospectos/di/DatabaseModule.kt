package com.example.capturadigitalmvildeprospectos.di

import android.content.Context
import androidx.room.Room
import com.example.capturadigitalmvildeprospectos.data.local.AppDatabase
import com.example.capturadigitalmvildeprospectos.data.local.ProspectoDao
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
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "prospectos_db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideProspectoDao(db: AppDatabase): ProspectoDao {
        return db.prospectoDao()
    }
}
package com.example.capturadigitalmvildeprospectos.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [ProspectoEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun prospectoDao(): ProspectoDao
}
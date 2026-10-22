package com.example.capturadigitalmvildeprospectos.papers.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [PaperEntity::class], version = 1, exportSchema = false)
abstract class PaperDatabase : RoomDatabase() {
    abstract fun paperDao(): PaperDao
}
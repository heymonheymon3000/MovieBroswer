package com.example.moviebrowser.cache.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.moviebrowser.cache.model.MovieEntity

@Database(entities = [MovieEntity::class ], version = 1)
abstract class AppDatabase:
    RoomDatabase() {
}
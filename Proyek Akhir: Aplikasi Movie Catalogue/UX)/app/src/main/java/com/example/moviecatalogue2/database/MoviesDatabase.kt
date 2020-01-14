package com.example.moviecatalogue2.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.moviecatalogue2.data.Film

@Database(entities = arrayOf(Film::class), version = 1, exportSchema = false)
@TypeConverters(Converter::class)

abstract class MoviesDatabase : RoomDatabase() {

    abstract fun MoviesDao(): MoviesDao

    companion object {
        private var INSTANCE: MoviesDatabase? = null

        fun getInstance(context: Context): MoviesDatabase? {
            if (INSTANCE == null) {
                synchronized(MoviesDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        MoviesDatabase::class.java,
                        "moviesdata.db"
                    ).fallbackToDestructiveMigration()
                        .allowMainThreadQueries()
                        .build()
                }
            }
            return INSTANCE
        }
    }
}
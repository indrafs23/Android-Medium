package com.example.moviecatalogue2.database

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.example.moviecatalogue2.data.Film

@Dao
interface MoviesDao {
    @Query("SELECT * from movies")
    fun getAllMovies(): List<Film>

    @Insert(onConflict = REPLACE)
    fun insertMovies(dataMovies: Film)

    @Delete
    fun DeleteMovies(dataMovies: Film)

    @Update
    fun UpdateMovies(dataMovies: Film)

    @Query("SELECT * FROM movies WHERE id LIKE :search")
    fun searchId(search: Int): Film
}
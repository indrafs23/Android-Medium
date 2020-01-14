package com.example.moviecatalogue2.database

import android.database.Cursor
import androidx.lifecycle.LiveData
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
    fun deleteMovies(dataMovies: Film)

    @Update
    fun updateMovies(dataMovies: Film)

    @Query("SELECT * FROM movies WHERE id LIKE :search")
    fun searchId(search: Int): Film

    @Query("SELECT * FROM movies  WHERE id LIKE :query")
    fun getMoviesName(query: String): LiveData<List<Film>>

    @Query("SELECT * from movies")
    fun getAllMoviesCursor(): Cursor
}
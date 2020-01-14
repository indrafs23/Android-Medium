package com.example.moviecatalogue2.database

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.example.moviecatalogue2.data.TvShow

@Dao
interface TvShowDao {
    @Query("SELECT * from tvShow")
    fun getAllTvShow(): List<TvShow>

    @Insert(onConflict = REPLACE)
    fun insertTvShow(dataTvShow: TvShow)

    @Delete
    fun deleteTvShow(dataTvShow: TvShow)

    @Update
    fun updateTvShow(dataTvShow: TvShow)

    @Query("SELECT * FROM tvShow WHERE id LIKE :search")
    fun searchId(search: Int): TvShow
}
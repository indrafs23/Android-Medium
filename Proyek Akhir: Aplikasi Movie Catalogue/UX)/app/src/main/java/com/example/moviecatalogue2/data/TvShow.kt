package com.example.moviecatalogue2.data

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.moviecatalogue2.database.Converter
import kotlinx.android.parcel.Parcelize

@Parcelize

@Entity(tableName = "tvShow")

data class TvShow(
    @ColumnInfo(name = "original_name") var original_name: String ?= "",
    @ColumnInfo(name = "genre_ids") @TypeConverters(Converter::class) var genre_ids: List<Int> ?= emptyList(),
    @ColumnInfo(name = "name") var name: String ?= "",
    @ColumnInfo(name = "popularity") var popularity: String ?= "",
    @ColumnInfo(name = "origin_country") @TypeConverters(Converter::class) var origin_country: List<String> ?= emptyList(),
    @ColumnInfo(name = "vote_count") var vote_count: String ?= "",
    @ColumnInfo(name = "first_air_date") var first_air_date: String ?= "",
    @ColumnInfo(name = "backdrop_path") var backdrop_path: String ?= "",
    @ColumnInfo(name = "original_language") var original_language: String ?= "",
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") var id: Int = -1,
    @ColumnInfo(name = "vote_average") var vote_average: String ?= "",
    @ColumnInfo(name = "overview") var overview: String ?= "",
    @ColumnInfo(name = "poster_path") var poster_path: String ?= ""
) : Parcelable
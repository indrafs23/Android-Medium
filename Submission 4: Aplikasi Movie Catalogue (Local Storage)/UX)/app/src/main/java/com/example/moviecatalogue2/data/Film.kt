package com.example.moviecatalogue2.data

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.moviecatalogue2.database.Converter
import kotlinx.android.parcel.Parcelize

@Parcelize

@Entity(tableName = "movies")
data class Film(
    @ColumnInfo(name = "popularity") var popularity: String ?= "",
    @ColumnInfo(name = "vote_count") var vote_count: String ?= "",
    @ColumnInfo(name = "video") var video: String ?= "",
    @ColumnInfo(name = "poster_path") var poster_path: String ?= "",
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") var id: Int = -1,
    @ColumnInfo(name = "adult") var adult: String ?= "",
    @ColumnInfo(name = "backdrop_path") var backdrop_path: String ?= "",
    @ColumnInfo(name = "original_languange") var original_languange: String ?= "",
    @ColumnInfo(name = "original_title") var original_title: String ?= "",
    @ColumnInfo(name = "genre_ids") @TypeConverters(Converter::class) var genre_ids: List<Int> ?= emptyList(),
    @ColumnInfo(name = "title") var title: String ?= "",
    @ColumnInfo(name = "vote_average") var vote_average: String ?= "",
    @ColumnInfo(name = "overview") var overview: String ?= "",
    @ColumnInfo(name = "release_date") var release_date: String ?= ""
) : Parcelable

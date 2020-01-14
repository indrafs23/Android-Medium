package com.example.moviecatalogue2.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TvShow(
    var original_name: String? = "",
    var genre_ids: List<Int>,
    var name: String? = "",
    var popularity: String? = "",
    var origin_country: List<String>,
    var vote_count: String? = "",
    var first_air_date: String? = "",
    var backdrop_path: String? = "",
    var original_language: String? = "",
    var id: String? = "",
    var vote_average: String? = "",
    var overview: String? = "",
    var poster_path: String? = ""
) : Parcelable
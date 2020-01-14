package com.example.favoritefilm.Data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Film(
    var popularity: String? = "",
    var vote_count: String? = "",
    var video: String? = "",
    var poster_path: String? = "",
    var id: Int = -1,
    var adult: String? = "",
    var backdrop_path: String? = "",
    var original_languange: String? = "",
    var original_title: String? = "",
    var genre_ids: List<Int>? = emptyList(),
    var title: String? = "",
    var vote_average: String? = "",
    var overview: String? = "",
    var release_date: String? = ""
) : Parcelable
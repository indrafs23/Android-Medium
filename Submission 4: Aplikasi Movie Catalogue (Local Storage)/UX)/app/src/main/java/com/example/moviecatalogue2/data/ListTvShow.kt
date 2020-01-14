package com.example.moviecatalogue2.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class ListTvShow(
    var page: Int = 0,
    var total_results: Int = 0,
    var total_pages: Int = 0,
    var results: List<TvShow> = emptyList()
) : Parcelable
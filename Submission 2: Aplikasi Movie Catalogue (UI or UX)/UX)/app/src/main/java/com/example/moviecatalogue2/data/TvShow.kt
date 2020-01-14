package com.example.moviecatalogue2.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TvShow (
    var name:String = "",
    var popularity : String = "",
    var date : String = "",
    var desk:String = "",
    var photo:String = "",
    var creator:String = ""
) : Parcelable
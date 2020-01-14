package com.example.moviecatalogue.Data

import android.annotation.SuppressLint
import android.os.Parcel
import android.os.Parcelable

@SuppressLint("ParcelCreator")
data class Film (
    var name:String = "",
    var popularity : String = "",
    var date : String = "",
    var desk:String = "",
    var photo:String = "",
    var director:String = ""
) : Parcelable {

    companion object {
        @JvmField
        val CREATOR = object : Parcelable.Creator<Film> {
            override fun createFromParcel(parcel: Parcel) = Film(parcel)
            override fun newArray(size: Int) = arrayOfNulls<Film>(size)
        }
    }

    private constructor(parcel: Parcel) : this(
        name = parcel.readString(),
        popularity = parcel.readString(),
        date = parcel.readString(),
        desk = parcel.readString(),
        photo = parcel.readString(),
        director = parcel.readString()
    )

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeString(this.name)
        dest?.writeString(this.popularity)
        dest?.writeString(this.date)
        dest?.writeString(this.desk)
        dest?.writeString(this.photo)
        dest?.writeString(this.director)
    }

    override fun describeContents(): Int {
        return 0
    }
}

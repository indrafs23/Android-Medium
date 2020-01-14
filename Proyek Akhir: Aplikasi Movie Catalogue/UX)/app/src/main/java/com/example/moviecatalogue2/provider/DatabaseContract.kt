package com.example.moviecatalogue2.provider

import android.net.Uri
import android.provider.BaseColumns

object DatabaseContract {
    const val TABLE_NAME = "movies"
    const val AUTHORITY = "com.example.moviecatalogue2"

    class MovieColumns : BaseColumns {
        companion object {
            const val ID = -1
            const val TITLE = "title"
            const val OVERVIEW = "overview"
            const val RELEASE = "release_date"
            const val IMAGE = "poster_path"
            const val VOTE_AVERANGE = "vote_average"

            val CONTENT_URI = Uri.Builder().scheme("content")
                .authority(AUTHORITY)
                .appendPath(TABLE_NAME)
                .build()
        }
    }
}
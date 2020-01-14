package com.example.favoritefilm.adapter

import android.net.Uri
import android.provider.BaseColumns

object DatabaseContract {
    const val TABLE_NAME = "movies"
    const val AUTHORITY = "com.example.moviecatalogue2"

    class MovieColumns : BaseColumns {
        companion object {
            const val ID = "id"
            const val TITLE = "title"
            const val OVERVIEW = "overview"
            const val RELEASE = "release_date"
            const val IMAGE = "poster_path"
            const val VOTE_AVERANGE = "vote_average"
            const val POPULARITY = "popularity"
            const val VOTECOUNT = "vote_count"
            const val VIDEO = "video"
            const val ADULT = "adult"
            const val BACKDROP = "backdrop_path"
            const val ORIGINALLANGUANGE = "original_languange"
            const val ORIGINALTITLE = "original_title"
            const val GENRE = "genre_ids"

            val CONTENT_URI = Uri.Builder().scheme("content")
                .authority(AUTHORITY)
                .appendPath(TABLE_NAME)
                .build()
        }
    }

}
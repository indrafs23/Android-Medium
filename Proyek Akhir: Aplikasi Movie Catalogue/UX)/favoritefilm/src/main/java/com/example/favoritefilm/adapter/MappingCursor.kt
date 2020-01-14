package com.example.favoritefilm.adapter

import android.database.Cursor
import com.example.favoritefilm.Data.Film
import com.example.favoritefilm.adapter.DatabaseContract.MovieColumns.Companion.ADULT
import com.example.favoritefilm.adapter.DatabaseContract.MovieColumns.Companion.BACKDROP
import com.example.favoritefilm.adapter.DatabaseContract.MovieColumns.Companion.GENRE
import com.example.favoritefilm.adapter.DatabaseContract.MovieColumns.Companion.ID
import com.example.favoritefilm.adapter.DatabaseContract.MovieColumns.Companion.IMAGE
import com.example.favoritefilm.adapter.DatabaseContract.MovieColumns.Companion.ORIGINALLANGUANGE
import com.example.favoritefilm.adapter.DatabaseContract.MovieColumns.Companion.ORIGINALTITLE
import com.example.favoritefilm.adapter.DatabaseContract.MovieColumns.Companion.OVERVIEW
import com.example.favoritefilm.adapter.DatabaseContract.MovieColumns.Companion.POPULARITY
import com.example.favoritefilm.adapter.DatabaseContract.MovieColumns.Companion.RELEASE
import com.example.favoritefilm.adapter.DatabaseContract.MovieColumns.Companion.TITLE
import com.example.favoritefilm.adapter.DatabaseContract.MovieColumns.Companion.VIDEO
import com.example.favoritefilm.adapter.DatabaseContract.MovieColumns.Companion.VOTECOUNT
import com.example.favoritefilm.adapter.DatabaseContract.MovieColumns.Companion.VOTE_AVERANGE
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*
import kotlin.collections.ArrayList

object MappingCursor {
    fun mapCursorToArrayList(notesCursor: Cursor): ArrayList<Film> {
        val notesList = ArrayList<Film>()

        while (notesCursor.moveToNext()) {
            val id = notesCursor.getInt(notesCursor.getColumnIndexOrThrow(ID))
            val title = notesCursor.getString(notesCursor.getColumnIndexOrThrow(TITLE))
            val overview = notesCursor.getString(notesCursor.getColumnIndexOrThrow(OVERVIEW))
            val release = notesCursor.getString(notesCursor.getColumnIndexOrThrow(RELEASE))
            val image = notesCursor.getString(notesCursor.getColumnIndexOrThrow(IMAGE))
            val voteAverage = notesCursor.getString(notesCursor.getColumnIndexOrThrow(VOTE_AVERANGE))
            val popularity = notesCursor.getString(notesCursor.getColumnIndexOrThrow(POPULARITY))
            val votecount = notesCursor.getString(notesCursor.getColumnIndexOrThrow(VOTECOUNT))
            val video = notesCursor.getString(notesCursor.getColumnIndexOrThrow(VIDEO))
            val adult = notesCursor.getString(notesCursor.getColumnIndexOrThrow(ADULT))
            val backdrop = notesCursor.getString(notesCursor.getColumnIndexOrThrow(BACKDROP))
            val orilanguange = notesCursor.getString(notesCursor.getColumnIndexOrThrow(
                ORIGINALLANGUANGE))
            val originaltitle = notesCursor.getString(notesCursor.getColumnIndexOrThrow(
                ORIGINALTITLE))
            val genre = notesCursor.getString(notesCursor.getColumnIndexOrThrow(GENRE))
            notesList.add(Film(popularity, votecount, video, image, id, adult, backdrop, orilanguange, originaltitle, stringToInt(genre), title, voteAverage, overview, release))
        }

        return notesList
    }

    fun stringToInt(data: String?): List<Int> {
        val gson = Gson()

        if (data == null) {
            return Collections.emptyList()
        }

        val listType = object : TypeToken<List<Int>>() {

        }.type

        return gson.fromJson(data, listType)
    }

    fun mapCursorToObject(notesCursor: Cursor): Film {
        notesCursor.moveToNext()
        val id = notesCursor.getInt(notesCursor.getColumnIndexOrThrow(ID.toString()))
        val title = notesCursor.getString(notesCursor.getColumnIndexOrThrow(TITLE))
        val overview = notesCursor.getString(notesCursor.getColumnIndexOrThrow(OVERVIEW))
        val release = notesCursor.getString(notesCursor.getColumnIndexOrThrow(RELEASE))
        val image = notesCursor.getString(notesCursor.getColumnIndexOrThrow(IMAGE))
        val voteAverage = notesCursor.getString(notesCursor.getColumnIndexOrThrow(VOTE_AVERANGE))
        val popularity = notesCursor.getString(notesCursor.getColumnIndexOrThrow(POPULARITY))
        val votecount = notesCursor.getString(notesCursor.getColumnIndexOrThrow(VOTECOUNT))
        val video = notesCursor.getString(notesCursor.getColumnIndexOrThrow(VIDEO))
        val adult = notesCursor.getString(notesCursor.getColumnIndexOrThrow(ADULT))
        val backdrop = notesCursor.getString(notesCursor.getColumnIndexOrThrow(BACKDROP))
        val orilanguange = notesCursor.getString(notesCursor.getColumnIndexOrThrow(
            ORIGINALLANGUANGE))
        val originaltitle = notesCursor.getString(notesCursor.getColumnIndexOrThrow(
            ORIGINALTITLE))
        val genre = notesCursor.getString(notesCursor.getColumnIndexOrThrow(GENRE))
        return Film(popularity, votecount, video, image, id, adult, backdrop, orilanguange, originaltitle, stringToInt(genre), title, voteAverage, overview, release)
    }
}
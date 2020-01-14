package com.example.moviecatalogue2.provider

import android.content.ContentProvider
import android.content.ContentValues
import android.content.Context
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import com.example.moviecatalogue2.database.MoviesDatabase
import com.example.moviecatalogue2.provider.DatabaseContract.AUTHORITY
import com.example.moviecatalogue2.provider.DatabaseContract.TABLE_NAME

class FilmContentProvider : ContentProvider() {
    var result:Context ?= null

    companion object{
        private const val MOVIE = 1
        private const val MOVIE_ID = 2
        private lateinit var filmContentProvider: MoviesDatabase

        private val sUriMatcher = UriMatcher(UriMatcher.NO_MATCH)

        init {
            sUriMatcher.addURI(AUTHORITY,TABLE_NAME,1)
            sUriMatcher.addURI(AUTHORITY,TABLE_NAME + "/#",2)
        }
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun query(
        uri: Uri,
        projection: Array<String>?,
        selection: String?,
        selectionArgs: Array<String>?,
        sortOrder: String?
    ): Cursor? {
        val cursor: Cursor?
        when(sUriMatcher.match(uri)){
            MOVIE -> cursor = filmContentProvider.MoviesDao().getAllMoviesCursor()
            MOVIE_ID -> cursor = filmContentProvider.MoviesDao().getAllMoviesCursor()
            else -> cursor = null
        }
        return cursor
    }

    fun  getContext(context: Context){
        this.result = context
    }

    override fun onCreate(): Boolean {
        filmContentProvider = MoviesDatabase.getInstance(context as Context)!!
        filmContentProvider.isOpen
        return true
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<String>?
    ): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getType(uri: Uri): String? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
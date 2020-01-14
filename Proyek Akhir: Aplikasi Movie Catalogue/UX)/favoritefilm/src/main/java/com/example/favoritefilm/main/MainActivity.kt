package com.example.favoritefilm.main

import android.database.ContentObserver
import android.database.Cursor
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.favoritefilm.Data.Film
import com.example.favoritefilm.R
import com.example.favoritefilm.adapter.DatabaseContract.MovieColumns.Companion.CONTENT_URI
import com.example.favoritefilm.adapter.FilmFavoritAdapter
import com.example.favoritefilm.adapter.MappingCursor
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    private val listFilmAdapter = FilmFavoritAdapter()
    private var listFilm: ArrayList<Film>? = null

    companion object {
        private const val EXTRA_STATE = "EXTRA_STATE"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        moviesRecyclerView.setHasFixedSize(true)

        val handlerThread = HandlerThread("DataObserver")
        handlerThread.start()
        val handler = Handler(handlerThread.looper)

        val myObserver = object : ContentObserver(handler) {
            override fun onChange(self: Boolean) {
                loadNotesAsync()
            }
        }

        contentResolver.registerContentObserver(CONTENT_URI, true, myObserver)

        if (savedInstanceState == null) {
            loadNotesAsync()
        } else {
            val list = savedInstanceState.getParcelableArrayList<Film>(EXTRA_STATE)
            if (list != null) {
                listFilm = list
                listFilmAdapter.setNewDataFilm(listFilm!!)
                framelayout_fragment_film.visibility = View.GONE
                progressBar_fragment_film.visibility = View.GONE
            }
        }

        moviesRecyclerView.apply {
            moviesRecyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
            moviesRecyclerView.adapter = listFilmAdapter
            framelayout_fragment_film.visibility = View.GONE
            progressBar_fragment_film.visibility = View.GONE
        }
    }

    private fun loadNotesAsync() {
        GlobalScope.launch(Dispatchers.Main) {
            framelayout_fragment_film.visibility = View.VISIBLE
            progressBar_fragment_film.visibility = View.VISIBLE
            val deferredNotes = async(Dispatchers.IO) {
                val cursor = contentResolver?.query(CONTENT_URI, null, null, null, null) as Cursor
                Log.d("Masuk",cursor.count.toString())
                MappingCursor.mapCursorToArrayList(cursor)
            }
            val notes = deferredNotes.await()
            framelayout_fragment_film.visibility = View.VISIBLE
            progressBar_fragment_film.visibility = View.VISIBLE
            if (notes.size > 0) {
                listFilm = notes
                listFilmAdapter.setNewDataFilm(listFilm!!)
            } else {
                listFilm = ArrayList()
                listFilmAdapter.setNewDataFilm(listFilm!!)
            }
            framelayout_fragment_film.visibility = View.GONE
            progressBar_fragment_film.visibility = View.GONE
        }
    }
}

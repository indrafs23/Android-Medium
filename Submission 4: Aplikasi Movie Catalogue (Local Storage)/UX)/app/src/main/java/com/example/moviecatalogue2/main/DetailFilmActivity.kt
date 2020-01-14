package com.example.moviecatalogue2.main

import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.moviecatalogue2.R
import com.example.moviecatalogue2.data.Film
import com.example.moviecatalogue2.database.MoviesDatabase
import kotlinx.android.synthetic.main.activity_detail_film.*

class DetailFilmActivity : AppCompatActivity() {
    private lateinit var data: Film
    private var moviesDatabase: MoviesDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_film)
        framelayout_detail_film.visibility = View.VISIBLE
        progressBar_detail_film.visibility = View.VISIBLE

        data = (intent.extras.getParcelable("data") as Film)
        addBack()
        changeName(getString(R.string.film))

        showDetail()
    }

    private fun addBack() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun showDetail() {
        detail_film_name.setText(data.title)

        detail_film_date.setText(Html.fromHtml("<b>${this.getString(R.string.date)} </b> <br> ${data.release_date}"))

        detail_film_popularity.setText(Html.fromHtml("<b>${this.getString(R.string.rating)} </b> <br> ${data.vote_average}"))

        detail_film_description.setText(Html.fromHtml("<b>${this.getString(R.string.overview)} </b> <br> ${data.overview}"))

        detail_film_director.setText(Html.fromHtml("<b>${this.getString(R.string.vote_count)}</b> <br> ${data.vote_count}"))

        Glide.with(this)
            .load(this.getString(R.string.path_url_image) + data.poster_path)
            .into(detail_film_photo)

        framelayout_detail_film.visibility = View.GONE
        progressBar_detail_film.visibility = View.GONE
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        setMode(item.itemId)
        return super.onOptionsItemSelected(item)
    }

    private fun setMode(itemId: Int) {
        when (itemId) {
            android.R.id.home -> {
                finish()
            }
            R.id.action_list_favorite_movies -> {
                moviesDatabase = MoviesDatabase.getInstance(this)
                if (moviesDatabase?.MoviesDao()?.getAllMovies()?.isEmpty()!!) {
                    moviesDatabase?.MoviesDao()?.insertMovies(data)
                    val toast = Toast.makeText(
                        this,
                        getString(R.string.addMovie),
                        Toast.LENGTH_SHORT
                    )
                    toast.show()
                } else if (moviesDatabase?.MoviesDao()?.searchId(data.id) == null) {
                    moviesDatabase?.MoviesDao()?.insertMovies(data)
                    val toast = Toast.makeText(
                        this,
                        getString(R.string.addMovie),
                        Toast.LENGTH_SHORT
                    )
                    toast.show()
                } else {
                    val toast = Toast.makeText(
                        this,
                        "${data.original_title} " + getString(R.string.selectMoviesTvShow),
                        Toast.LENGTH_SHORT
                    )
                    toast.show()
                }
            }
        }
    }

    private fun changeName(title: String) {
        supportActionBar?.title = title
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_detail_movies, menu)
        return super.onCreateOptionsMenu(menu)
    }
}

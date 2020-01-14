package com.example.moviecatalogue2.fragment

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviecatalogue2.R
import com.example.moviecatalogue2.adapter.ListFavoriteFilmAdapter
import com.example.moviecatalogue2.adapter.ListFilmAdapter
import com.example.moviecatalogue2.data.Film
import com.example.moviecatalogue2.database.MoviesDatabase
import com.example.moviecatalogue2.main.DetailFilmActivity
import kotlinx.android.synthetic.main.fragment_favorite_movies.*

/**
 * A simple [Fragment] subclass.
 */
class FavoriteMoviesFragment : Fragment() {
    private var movies: MoviesDatabase? = null
    private val listFilmAdapter = ListFavoriteFilmAdapter()
    private var listFilm: ArrayList<Film>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        framelayout_movies_favorite.visibility = View.VISIBLE
        progressBar_movies_favorite.visibility = View.VISIBLE

        getCurrentData()


        moviesFavoriteRecyclerView.apply {
            framelayout_movies_favorite.visibility = View.GONE
            progressBar_movies_favorite.visibility = View.GONE

            moviesFavoriteRecyclerView.layoutManager = LinearLayoutManager(activity)
            moviesFavoriteRecyclerView.adapter = listFilmAdapter
            (moviesFavoriteRecyclerView.adapter as ListFavoriteFilmAdapter).notifyDataSetChanged()

            listFilmAdapter.setOnItemClickCallback(object : ListFavoriteFilmAdapter.OnItemClickCallback {
                override fun onItemClicked(data: Film) {
                    val moveDetail = Intent(getActivity(), DetailFilmActivity::class.java)
                    moveDetail.putExtra("data", data as Parcelable)
                    startActivity(moveDetail)
                }
            })
        }
    }

    private fun getCurrentData() {
        movies = activity?.let { MoviesDatabase.getInstance(it) }
        listFilm = movies?.MoviesDao()?.getAllMovies() as ArrayList<Film>

        Log.d("Movies","${listFilm.toString()}")
        if (listFilm != null) {
            listFilmAdapter.setNewDataFilm(listFilm!!)
        } else {
            framelayout_movies_favorite.visibility = View.GONE
            progressBar_movies_favorite.visibility = View.GONE
            val toast = Toast.makeText(
                activity,
                R.string.listMoviesTvShow,
                Toast.LENGTH_SHORT
            )
            toast.show()
        }
    }
}

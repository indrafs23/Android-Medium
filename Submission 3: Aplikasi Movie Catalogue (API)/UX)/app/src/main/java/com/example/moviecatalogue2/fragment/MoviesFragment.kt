package com.example.moviecatalogue2.fragment


import Film
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Parcelable
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import com.example.moviecatalogue2.R
import com.example.moviecatalogue2.adapter.ListFilmAdapter
import com.example.moviecatalogue2.data.ListFilm
import com.example.moviecatalogue2.data.TmdbApi
import com.example.moviecatalogue2.data.TvShow
import com.example.moviecatalogue2.main.DetailFilmActivity
import kotlinx.android.synthetic.main.fragment_movies.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.math.log

/**
 * A simple [Fragment] subclass.
 */
class MoviesFragment : Fragment() {
    private val listFilmAdapter = ListFilmAdapter()
    private var listFilm: ArrayList<Film>? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        moviesRecyclerView.setHasFixedSize(true)
        framelayout2.visibility = View.VISIBLE
        progressBar.visibility = View.VISIBLE

        getCurrentData()

        moviesRecyclerView.apply {
            moviesRecyclerView.layoutManager = LinearLayoutManager(activity)
            moviesRecyclerView.adapter = listFilmAdapter

            listFilmAdapter.setOnItemClickCallback(object : ListFilmAdapter.OnItemClickCallback {
                override fun onItemClicked(data: Film) {
                    val moveDetail = Intent(getActivity(), DetailFilmActivity::class.java)
                    moveDetail.putExtra("data", data as Parcelable)
                    startActivity(moveDetail)
                }
            })
        }

        if (savedInstanceState == null) {
            getCurrentData()
        } else {
            framelayout2.visibility = View.GONE
            progressBar.visibility = View.GONE
            listFilm = savedInstanceState.getParcelableArrayList("movies")
            listFilm?.let { listFilmAdapter.setNewDataFilm(it) }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putParcelableArrayList("movies", listFilm)
        super.onSaveInstanceState(outState)
    }

    fun getCurrentData() {
        val retrofit = Retrofit.Builder()
            .baseUrl(TmdbApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val movieApi = retrofit.create(TmdbApi::class.java)
        val call = movieApi.getMovies(TmdbApi.API_KEY, TmdbApi.LANGUAGE)
        call.enqueue(object : Callback<ListFilm> {
            override fun onFailure(call: Call<ListFilm>, t: Throwable) {
                val toast = Toast.makeText(
                    activity,
                    "Please check your internet connection.",
                    Toast.LENGTH_LONG
                )
                toast.show()
            }

            override fun onResponse(call: Call<ListFilm>, response: Response<ListFilm>) {
                if (response.code() == 200) {
                    val filmResponse = response.body() as ListFilm
                    listFilm = ArrayList(filmResponse.results)
                    listFilmAdapter.setNewDataFilm(filmResponse.results)

                    if (framelayout2 != null && progressBar != null) {
                        framelayout2.visibility = View.GONE
                        progressBar.visibility = View.GONE
                    }
                }
            }
        })
    }
}

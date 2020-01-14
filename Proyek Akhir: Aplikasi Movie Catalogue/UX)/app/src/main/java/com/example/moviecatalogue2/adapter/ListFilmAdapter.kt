package com.example.moviecatalogue2.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.moviecatalogue2.R
import com.example.moviecatalogue2.data.Film
import com.example.moviecatalogue2.data.ListFilm
import com.example.moviecatalogue2.data.TmdbApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ListFilmAdapter() : RecyclerView.Adapter<ListFilmAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback
    private var listFilm: List<Film> = emptyList()

    fun setNewDataFilm(list: List<Film>) {
        listFilm = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ListViewHolder {
        val view: View =
            LayoutInflater.from(p0.context).inflate(R.layout.item_row_movies, p0, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listFilm.size
    }

    override fun onBindViewHolder(p0: ListViewHolder, p1: Int) {
        val film: Film = listFilm[p1]

        p0.filmPhoto?.let {
            Glide.with(p0.itemView.context)
                .load(p0.context.getString(R.string.path_url_image) + film.poster_path)
                .apply(RequestOptions().override(96, 120))
                .into(it)
        }

        p0.filmName?.text = film.title
        p0.filmDescription?.text = film.overview
        p0.filmDate?.text = film.release_date
        p0.filmPopularity?.text = "${p0.context.getString(R.string.rating)} : ${film.vote_average}"

        p0.itemView.setOnClickListener { onItemClickCallback.onItemClicked(listFilm[p0.adapterPosition]) }
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val context: Context = itemView.context
        val filmName: TextView? = itemView.findViewById(R.id.film_name)
        val filmDescription: TextView? = itemView.findViewById(R.id.film_description)
        val filmPhoto: ImageView? = itemView.findViewById(R.id.film_photo)
        val filmPopularity: TextView? = itemView.findViewById(R.id.film_popularity)
        val filmDate: TextView? = itemView.findViewById(R.id.film_date)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Film)
    }

    internal fun filterList(text1: String) {
        if (!text1.isEmpty() || !text1.equals("")) {
            val retrofit = Retrofit.Builder()
                .baseUrl(TmdbApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val movieApi = retrofit.create(TmdbApi::class.java)
            val call = movieApi.getSearchMovie(TmdbApi.API_KEY, TmdbApi.LANGUAGE, text1)

            call.enqueue(object : Callback<ListFilm> {
                override fun onFailure(call: Call<ListFilm>, t: Throwable) {
                }

                override fun onResponse(call: Call<ListFilm>, response: Response<ListFilm>) {
                    if (response.code() == 200) {
                        val filmResponse = response.body() as ListFilm
                        listFilm = ArrayList(filmResponse.results)
                    }
                }
            })

            notifyDataSetChanged()
        }
    }
}
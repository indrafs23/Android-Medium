package com.example.favoritefilm.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.favoritefilm.Data.Film
import com.example.favoritefilm.R

class FilmFavoritAdapter : RecyclerView.Adapter<FilmFavoritAdapter.ListViewHolder>() {
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
}
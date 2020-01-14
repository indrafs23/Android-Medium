package com.example.moviecatalogue.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.moviecatalogue.Data.Film
import com.example.moviecatalogue.R

class AdapterFilm (val listFilm: ArrayList<Film>, val context:Context) : BaseAdapter() {
    private lateinit var viewHolder: ViewHolder

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        var view: View? = convertView

        if (parent != null) {
            view = LayoutInflater.from(parent.context).inflate(R.layout.row_film, parent, false )
        }

        viewHolder = ViewHolder(view)

        val film:Film = getItem(position)

        if (viewHolder != null) {
            viewHolder.bind(film)
        }

        return view

    }

    override fun getItem(position: Int): Film {
        return listFilm.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return listFilm.size
    }

    inner class ViewHolder(itemView: View?){
        val filmName: TextView? = itemView?.findViewById(R.id.film_nama)
        val filmDeskripsi: TextView? = itemView?.findViewById(R.id.film_deskripsi)
        val filmPhoto: ImageView? = itemView?.findViewById(R.id.film_photo)
        val filmPopularity: TextView? = itemView?.findViewById(R.id.film_popularity)
        val filmDate: TextView? = itemView?.findViewById(R.id.film_date)

        fun bind(film: Film){
            filmName?.text = film.name
            filmDeskripsi?.text = film.desk
            filmDate?.text = film.date
            filmPopularity?.text = "Popularity : ${film.popularity}"

            filmPhoto?.context?.let {
                Glide.with(it)
                    .load(film.photo)
                    .apply(RequestOptions().override(100,100))
                    .into(filmPhoto)
            }

        }

    }
}
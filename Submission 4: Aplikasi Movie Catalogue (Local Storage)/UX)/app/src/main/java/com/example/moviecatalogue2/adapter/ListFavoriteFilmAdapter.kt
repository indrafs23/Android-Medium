package com.example.moviecatalogue2.adapter

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import android.widget.Toast.*
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.moviecatalogue2.R
import com.example.moviecatalogue2.data.Film
import com.example.moviecatalogue2.database.MoviesDatabase

class ListFavoriteFilmAdapter() : RecyclerView.Adapter<ListFavoriteFilmAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback
    private var listFilm: List<Film> = emptyList()
    private var moviesDatabase: MoviesDatabase? = null

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

    @SuppressLint("ClickableViewAccessibility")
    override fun onBindViewHolder(p0: ListViewHolder, p1: Int) {
        val (popularity, vote_count, video, poster_path, id, adult, backdrop_path, original_languange, original_title, genre_ids, title, vote_average, overview, release_date) = listFilm[p1]

        p0.filmPhoto?.let {
            Glide.with(p0.itemView.context)
                .load(p0.context.getString(R.string.path_url_image) + poster_path)
                .apply(RequestOptions().override(96, 120))
                .into(it)
        }

        p0.filmName?.text = title
        p0.filmName?.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.delete, 0)
        p0.filmName?.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                var dialog: AlertDialog
                val builder = AlertDialog.Builder(p0.context)
                builder.setTitle(p0.context.getString(R.string.title_alert_movie))
                builder.setMessage(p0.context.getString(R.string.body_alert_movie))

                builder.setPositiveButton(p0.context.getString(R.string.yes)) { dialog, which ->
                    moviesDatabase = MoviesDatabase.getInstance(p0.context)
                    moviesDatabase?.MoviesDao()?.DeleteMovies(listFilm[p1])
                    val toast = Toast.makeText(
                        p0.context,
                        p0.context.getString(R.string.toast_movie),
                        Toast.LENGTH_SHORT
                    )
                    toast.show()
                }

                builder.setNegativeButton(p0.context.getString(R.string.no)) { dialog, which ->

                }
                dialog = builder.create()
                dialog.show()

                return v?.onTouchEvent(event)!!
            }
        })
        p0.filmDescription?.text = overview
        p0.filmDate?.text = release_date
        p0.filmPopularity?.text = "${p0.context.getString(R.string.rating)} : $vote_average"

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
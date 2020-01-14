package com.example.moviecatalogue2.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.moviecatalogue2.R
import com.example.moviecatalogue2.data.TvShow
import com.example.moviecatalogue2.database.TvShowDatabase

class ListFavoriteTvShowAdapter() :
    RecyclerView.Adapter<ListFavoriteTvShowAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback
    private var listTvShow: List<TvShow> = emptyList()
    private var tvShowDatabase: TvShowDatabase? = null

    fun setNewDataTvShow(list: List<TvShow>) {
        listTvShow = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ListViewHolder {
        val view: View =
            LayoutInflater.from(p0.context).inflate(R.layout.item_row_tvshow, p0, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listTvShow.size
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onBindViewHolder(p0: ListViewHolder, p1: Int) {
        val (original_name, genre_ids, name, popularity, origin_country, vote_count, first_air_date, backdrop_path, original_language, id, vote_average, overview, poster_path) = listTvShow[p1]

        p0.filmPhoto?.let {
            Glide.with(p0.itemView.context)
                .load(p0.context.getString(R.string.path_url_image) + poster_path)
                .apply(RequestOptions().override(96, 120))
                .into(it)
        }

        p0.filmName?.text = name
        p0.filmName?.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.delete, 0)

        p0.filmName?.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                var dialog: AlertDialog
                val builder = AlertDialog.Builder(p0.context)
                builder.setTitle(p0.context.getString(R.string.title_alert_tvShow))
                builder.setMessage(p0.context.getString(R.string.body_alert_tvShow))

                builder.setPositiveButton(p0.context.getString(R.string.yes)) { dialog, which ->
                    tvShowDatabase = TvShowDatabase.getInstance(p0.context)
                    tvShowDatabase?.TvShowDao()?.deleteTvShow(listTvShow[p1])
                    val toast = Toast.makeText(
                        p0.context,
                        p0.context.getString(R.string.toast_tvShow),
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
        p0.filmDate?.text = first_air_date
        p0.filmPopularity?.text = "${p0.context.getString(R.string.rating)} : $vote_average"

        p0.itemView.setOnClickListener { onItemClickCallback.onItemClicked(listTvShow[p0.adapterPosition]) }
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val context: Context = itemView.context
        val filmName: TextView? = itemView.findViewById(R.id.tvShow_name)
        val filmDescription: TextView? = itemView.findViewById(R.id.tvShow_description)
        val filmPhoto: ImageView? = itemView.findViewById(R.id.tvShow_photo)
        val filmPopularity: TextView? = itemView.findViewById(R.id.tvShow_popularity)
        val filmDate: TextView? = itemView.findViewById(R.id.tvShow_date)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: TvShow)
    }
}
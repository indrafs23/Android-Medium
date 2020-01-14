package com.example.moviecatalogue2.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
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

    override fun onBindViewHolder(p0: ListViewHolder, p1: Int) {
        val tvShow:TvShow = listTvShow[p1]

        p0.filmPhoto?.let {
            Glide.with(p0.itemView.context)
                .load(p0.context.getString(R.string.path_url_image) + tvShow.poster_path)
                .apply(RequestOptions().override(96, 120))
                .into(it)
        }

        p0.filmName?.text = tvShow.name
        p0.filmDescription?.text = tvShow.overview
        p0.filmDate?.text = tvShow.first_air_date
        p0.filmPopularity?.text = "${p0.context.getString(R.string.rating)} : ${tvShow.vote_average}"

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
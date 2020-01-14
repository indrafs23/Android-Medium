package com.example.moviecatalogue2.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.moviecatalogue2.R
import com.example.moviecatalogue2.data.TvShow

class ListTvShowAdapter (val listTvShow: ArrayList<TvShow>) : RecyclerView.Adapter<ListTvShowAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ListViewHolder {
        val view: View = LayoutInflater.from(p0.context).inflate(R.layout.item_row_tvshow, p0,false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listTvShow.size
    }

    override fun onBindViewHolder(p0: ListViewHolder, p1: Int) {
        val (name, popularity, date, desk, photo) = listTvShow[p1]

        p0.filmPhoto?.let {
            Glide.with(p0.itemView.context)
                .load(photo)
                .apply(RequestOptions().override(96,120))
                .into(it)
        }

        p0.filmName?.text = name
        p0.filmDeskripsi?.text = desk
        p0.filmDate?.text = date
        p0.filmPopularity?.text = "Popularity : $popularity"

        p0.itemView.setOnClickListener { onItemClickCallback.onItemClicked(listTvShow[p0.adapterPosition]) }
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val filmName: TextView? = itemView?.findViewById(R.id.tvShow_nama)
        val filmDeskripsi: TextView? = itemView?.findViewById(R.id.tvShow_deskripsi)
        val filmPhoto: ImageView? = itemView?.findViewById(R.id.tvShow_photo)
        val filmPopularity: TextView? = itemView?.findViewById(R.id.tvShow_popularity)
        val filmDate: TextView? = itemView?.findViewById(R.id.tvShow_date)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: TvShow)
    }
}
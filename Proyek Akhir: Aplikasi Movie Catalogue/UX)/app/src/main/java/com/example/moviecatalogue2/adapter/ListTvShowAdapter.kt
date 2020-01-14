package com.example.moviecatalogue2.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.moviecatalogue2.R
import com.example.moviecatalogue2.data.ListTvShow
import com.example.moviecatalogue2.data.TmdbApi
import com.example.moviecatalogue2.data.TvShow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ListTvShowAdapter() : RecyclerView.Adapter<ListTvShowAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback
    private var listTvShow: List<TvShow> = emptyList()

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
        val tvShow= listTvShow[p1]

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

    internal fun filterList(text1:String) {
        if (!text1.isEmpty() || !text1.equals("")) {
            Log.d("Masuk", "jsahfjkshajfk")
           val retrofit = Retrofit.Builder()
               .baseUrl(TmdbApi.BASE_URL)
               .addConverterFactory(GsonConverterFactory.create())
               .build()

           val movieApi = retrofit.create(TmdbApi::class.java)
           val call = movieApi.getSearchTvShow(TmdbApi.API_KEY, TmdbApi.LANGUAGE, text1)

           call.enqueue(object : Callback<ListTvShow> {
               override fun onFailure(call: Call<ListTvShow>, t: Throwable) {
               }

               override fun onResponse(call: Call<ListTvShow>, response: Response<ListTvShow>) {
                   if (response.code() == 200) {
                       Log.d("Masuk", "panggil database")
                       val filmResponse = response.body() as ListTvShow
                       listTvShow = ArrayList(filmResponse.results)
                   }
               }
           })

           notifyDataSetChanged()
       }
    }
}
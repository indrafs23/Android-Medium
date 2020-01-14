package com.example.moviecatalogue2.widget

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.widget.RemoteViews
import android.widget.RemoteViewsService
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.moviecatalogue2.R
import com.example.moviecatalogue2.data.Film
import com.example.moviecatalogue2.database.MoviesDatabase


class StackRemoteViewsFactory(context: Context) : RemoteViewsService.RemoteViewsFactory {
    private val mContext: Context
    private val mWidgetItems: ArrayList<String> = ArrayList()
    private var moviesDatabase: MoviesDatabase? = null

    init {
        mContext = context
    }

    override fun onCreate() {

    }

    override fun onDataSetChanged() {
        getMovie()
    }

    private fun getMovie(){
        moviesDatabase = MoviesDatabase.getInstance(mContext)

        if (moviesDatabase?.MoviesDao()?.getAllMovies() != null) {
            val list: List<Film> = moviesDatabase?.MoviesDao()?.getAllMovies()!!

            for (list in list) {
                mWidgetItems.add("${mContext.getString(R.string.path_url_image)}${list.poster_path}")
            }
        }
    }

    override fun onDestroy() {

    }

    override fun getCount(): Int {
        return mWidgetItems.size
    }

    override fun getViewAt(position: Int): RemoteViews {
        val rv = RemoteViews(mContext.packageName, R.layout.widget_item)

        try {
            val bitmap: Bitmap = Glide.with(mContext)
                                    .asBitmap()
                                    .load(mWidgetItems.get(position))
                                    .apply(RequestOptions())
                                    .submit(512,512)
                                    .get()

            rv.setImageViewBitmap(R.id.imageView, bitmap)
        }catch (e:Exception){
            e.printStackTrace()
        }

        val extras = Bundle()
        extras.putInt(MovieWidget.EXTRA_ITEM, position)

        val fillInIntent = Intent()
        fillInIntent.putExtras(extras)
        rv.setOnClickFillInIntent(R.id.imageView, fillInIntent)

        return rv
    }

    override fun getLoadingView(): RemoteViews? {
        return null
    }

    override fun getViewTypeCount(): Int {
        return 1
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun hasStableIds(): Boolean {
        return true
    }
}

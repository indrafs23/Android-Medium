package com.example.moviecatalogue2.main

import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.moviecatalogue2.R
import com.example.moviecatalogue2.data.TvShow
import com.example.moviecatalogue2.database.TvShowDatabase
import kotlinx.android.synthetic.main.activity_detail_tv_show.*

class DetailTvShowActivity : AppCompatActivity() {
    private lateinit var data: TvShow
    private var tvShowDatabase: TvShowDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_tv_show)
        framelayout_detail_tvShow.visibility = View.VISIBLE
        progressBar_detail_tvShow.visibility = View.VISIBLE

        val bundle = intent.getBundleExtra("myBundle")
        data = bundle.getParcelable("bundle")as TvShow

        addBack()
        changeName(getString(R.string.tvShow))

        showDetail()

        btn_tvShow_set_favorite.setOnClickListener {
            tvShowDatabase = TvShowDatabase.getInstance(this)
            Log.d("Log_Data",tvShowDatabase.toString())
            if (tvShowDatabase?.TvShowDao()?.getAllTvShow() == null) {
                Log.d("Log_Data",tvShowDatabase.toString()+"1")
                tvShowDatabase?.TvShowDao()?.insertTvShow(data)
                val toast = Toast.makeText(
                    this,
                    getString(R.string.addTvShow),
                    Toast.LENGTH_SHORT
                )
                toast.show()
            } else if (tvShowDatabase?.TvShowDao()?.searchId(data.id) == null) {
                Log.d("Log_Data",tvShowDatabase.toString()+"2")
                tvShowDatabase?.TvShowDao()?.insertTvShow(data)
                val toast = Toast.makeText(
                    this,
                    getString(R.string.addTvShow),
                    Toast.LENGTH_SHORT
                )
                toast.show()
            } else {
                val toast = Toast.makeText(
                    this,
                    "${data.original_name} " + getString(R.string.selectMoviesTvShow) ,
                    Toast.LENGTH_SHORT
                )
                toast.show()
            }
        }

        btn_tvShow_remove_from_favorite.setOnClickListener {
            val dialog: AlertDialog
            val builder = AlertDialog.Builder(this)
            builder.setTitle(this.getString(R.string.title_alert_tvShow))
            builder.setMessage(this.getString(R.string.body_alert_tvShow))

            builder.setPositiveButton(this.getString(R.string.yes)) { dialog, which ->
                tvShowDatabase = TvShowDatabase.getInstance(this)
                tvShowDatabase?.TvShowDao()?.deleteTvShow(data)
                val toast = Toast.makeText(
                    this,
                    this.getString(R.string.toast_tvShow),
                    Toast.LENGTH_SHORT
                )
                toast.show()
            }
            builder.setNegativeButton(this.getString(R.string.no)) { dialog, which ->

            }
            dialog = builder.create()
            dialog.show()
        }
    }

    private fun addBack() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun showDetail() {
        detail_TvShow_name.setText(data.name)

        detail_TvShow_date.setText(Html.fromHtml("<b>${this.getString(R.string.date)}</b> <br> ${data.first_air_date}"))

        detail_TvShow_popularity.setText(Html.fromHtml("<b>${this.getString(R.string.rating)}</b> <br> ${data.vote_average}"))

        detail_TvShow_description.setText(Html.fromHtml("<b>${this.getString(R.string.overview)}</b> <br> ${data.overview}"))

        detail_TvShow_creator.setText(Html.fromHtml("<b>${this.getString(R.string.popularity)}</b> <br> ${data.popularity}"))

        Glide.with(this)
            .load(this.getString(R.string.path_url_image) + data.poster_path)
            .into(detail_TvShow_photo)

        framelayout_detail_tvShow.visibility = View.GONE
        progressBar_detail_tvShow.visibility = View.GONE

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        setMode(item.itemId)
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        return super.onCreateOptionsMenu(menu)
    }

    private fun setMode(itemId: Int) {
        when (itemId) {
            android.R.id.home -> {
                finish()
            }
        }
    }

    private fun changeName(title: String) {
        supportActionBar?.title = title
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}

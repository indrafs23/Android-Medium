package com.example.moviecatalogue2.main

import Film
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Html
import android.view.MenuItem
import android.view.View
import com.bumptech.glide.Glide
import com.example.moviecatalogue2.R
import kotlinx.android.synthetic.main.activity_detail_film.*

class DetailFilmActivity : AppCompatActivity() {
    private lateinit var data: Film

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_film)
        framelayout2.visibility = View.VISIBLE
        progressBar.visibility = View.VISIBLE

        data = (intent.extras.getParcelable("data") as Film)
        addBack()
        changeName(getString(R.string.film))

        showDetail()
    }

    private fun addBack() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun showDetail() {
        detailfilm_name.setText(data.title)

        detailfilm_tanggal.setText(Html.fromHtml("<b>${this.getString(R.string.date)} </b> <br> ${data.release_date}"))

        detailfilm_popularitas.setText(Html.fromHtml("<b>${this.getString(R.string.rating)} </b> <br> ${data.vote_average}"))

        detailfilm_deskripsi.setText(Html.fromHtml("<b>${this.getString(R.string.overview)} </b> <br> ${data.overview}"))

        detailfilm_director.setText(Html.fromHtml("<b>${this.getString(R.string.vote_count)}</b> <br> ${data.vote_count}"))

        Glide.with(this)
            .load(this.getString(R.string.path_url_image) + data.poster_path)
            .into(detailfilm_photo)

        framelayout2.visibility = View.GONE
        progressBar.visibility = View.GONE
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        setMode(item.itemId)
        return super.onOptionsItemSelected(item)
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

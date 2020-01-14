package com.example.moviecatalogue2.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Html
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.example.moviecatalogue2.R
import com.example.moviecatalogue2.data.TvShow
import kotlinx.android.synthetic.main.activity_detail_tv_show.*

class DetailTvShowActivity : AppCompatActivity() {
    private lateinit var data: TvShow

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_tv_show)

        data = (intent.extras.getParcelable("data") as TvShow)
        addBack()
        changeName(getString(R.string.tvShow))

        showDetail()
    }

    private fun addBack() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun showDetail(){
        detailTvShow_name.setText(data.name)

        detailTvShow_tanggal.setText(Html.fromHtml("<b>${this.getString(R.string.date)}</b> <br> ${data.date}"))

        detailTvShow_popularitas.setText(Html.fromHtml("<b>${this.getString(R.string.popularity)}</b> <br> ${data.popularity}"))

        detailTvShow_deskripsi.setText(Html.fromHtml("<b>${this.getString(R.string.overview)}</b> <br> ${data.desk}"))

        detailTvShow_creator.setText((Html.fromHtml("<b>${this.getString(R.string.creator)}</b> <br> ${data.creator}")))

        Glide.with(this)
            .load(data.photo)
            .into(detailTvShow_photo)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        setMode(item.itemId)
        return super.onOptionsItemSelected(item)
    }

    private fun setMode(itemId: Int) {
        when(itemId) {
            android.R.id.home -> {
                finish()
            }
        }
    }

    private fun changeName(title: String){
        supportActionBar?.title = title
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}

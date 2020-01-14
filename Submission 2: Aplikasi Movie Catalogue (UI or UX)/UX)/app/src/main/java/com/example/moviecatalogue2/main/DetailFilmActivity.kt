package com.example.moviecatalogue2.main

import Film
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Html
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.example.moviecatalogue2.R
import kotlinx.android.synthetic.main.activity_detail_film.*

class DetailFilmActivity : AppCompatActivity() {
    private lateinit var data: Film

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_film)

        data = (intent.extras.getParcelable("data") as Film)
        addBack()
        changeName(getString(R.string.film))

        showDetail()
    }

    private fun addBack() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun showDetail(){
        detailfilm_name.setText(data.name)

        detailfilm_tanggal.setText(Html.fromHtml("<b>${this.getString(R.string.date)} </b> <br> ${data.date}"))

        detailfilm_popularitas.setText(Html.fromHtml("<b>${this.getString(R.string.popularity)} </b> <br> ${data.popularity}"))

        detailfilm_deskripsi.setText(Html.fromHtml("<b>${this.getString(R.string.overview)} </b> <br> ${data.desk}"))

        detailfilm_director.setText((Html.fromHtml("<b>${this.getString(R.string.director)} </b> <br> ${data.director}")))

        Glide.with(this)
            .load(data.photo)
            .into(detailfilm_photo)
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

package com.example.moviecatalogue.Main

import android.os.Bundle
import android.text.SpannableStringBuilder
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.bold
import com.bumptech.glide.Glide
import com.example.moviecatalogue.Data.Film
import com.example.moviecatalogue.R
import kotlinx.android.synthetic.main.activity_detail.*

class DetailFilm : AppCompatActivity() {
    private lateinit var data: Film

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        data = (intent.extras.getParcelable("data") as? Film)!!
        addBack()

        showDetail()

    }

    private fun addBack() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun showDetail(){
        detail_name.setText(data.name)

        detail_tanggal.setText(SpannableStringBuilder()
            .bold { append("Date \n") }
            .append(data.date))

        detail_popularitas.setText(SpannableStringBuilder()
            .bold { append("Popularity \n") }
            .append(data.popularity))

        detail_deskripsi.setText(SpannableStringBuilder()
            .bold { append("Overview \n") }
            .append(data.desk))

        detail_director.setText(SpannableStringBuilder()
            .bold { append("Director \n") }
            .append(data.director))

        Glide.with(this)
            .load(data.photo)
            .into(detail_photo)
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
}
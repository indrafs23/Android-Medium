package com.example.moviecatalogue.Main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.widget.AdapterView
import android.widget.ListView
import com.example.moviecatalogue.Adapter.AdapterFilm
import com.example.moviecatalogue.Data.Film
import com.example.moviecatalogue.Data.FilmData
import android.view.View
import com.example.moviecatalogue.R


class MainActivity : AppCompatActivity() {
    private lateinit var adapter:AdapterFilm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = AdapterFilm(FilmData.listData,this)

        val listFilm: ListView = findViewById(com.example.moviecatalogue.R.id.film_list)

        listFilm.setAdapter(adapter)

        listFilm.setOnItemClickListener(object : AdapterView.OnItemClickListener {
            override fun onItemClick( parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val moveDetail = Intent(this@MainActivity, DetailFilm::class.java)
                var data:Film = FilmData.listData.get(position)

                moveDetail.putExtra("data", data as Parcelable)
                startActivity(moveDetail)
            }
        })

    }
}


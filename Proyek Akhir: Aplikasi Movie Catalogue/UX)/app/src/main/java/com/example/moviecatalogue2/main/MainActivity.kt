package com.example.moviecatalogue2.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.moviecatalogue2.R
import com.example.moviecatalogue2.fragment.FavoriteFragment
import com.example.moviecatalogue2.fragment.MoviesFragment
import com.example.moviecatalogue2.fragment.TvShowFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        changeName(getString(R.string.title))

        if (savedInstanceState == null) {
            val fragment = MoviesFragment()
            supportFragmentManager.beginTransaction().replace(R.id.mainActivity, fragment)
                .commit()
        }

        navigation.setOnNavigationItemSelectedListener { item: MenuItem ->
            setMode(item.itemId)
            true
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        setMode(item.itemId)
        return super.onOptionsItemSelected(item)
    }

    private fun changeName(title: String) {
        supportActionBar?.title = title
    }

    private fun setMode(selectedMode: Int) {
        when (selectedMode) {
            R.id.action_list -> {
                val moveDetail = Intent(this, SettingsActivity::class.java)
                startActivity(moveDetail)
            }
            R.id.navigation_movie -> {
                val fragment = MoviesFragment()
                supportFragmentManager.beginTransaction().replace(R.id.mainActivity, fragment)
                    .commit()
            }
            R.id.navigation_tvShow -> {
                val fragment = TvShowFragment()
                supportFragmentManager.beginTransaction().replace(R.id.mainActivity, fragment)
                    .commit()
            }
            R.id.navigation_favorite -> {
                val fragment = FavoriteFragment()
                supportFragmentManager.beginTransaction().replace(R.id.mainActivity, fragment)
                    .commit()
            }
        }
    }
}

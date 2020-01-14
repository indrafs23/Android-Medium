package com.example.moviecatalogue2.main

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.example.moviecatalogue2.R
import com.example.moviecatalogue2.adapter.FragmentAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        changeName(getString(R.string.title))
        viewFrangment.adapter = FragmentAdapter(supportFragmentManager, this)
        tabs_main.setupWithViewPager(viewFrangment)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        setMode(item.itemId)
        return super.onOptionsItemSelected(item)
    }

    private fun changeName(title: String){
        supportActionBar?.title = title
    }

    private fun setMode(selectedMode: Int) {
        when (selectedMode) {
            R.id.action_list -> {
                val moveDetail = Intent(Settings.ACTION_LOCALE_SETTINGS)
                startActivity(moveDetail)
            }
        }
    }

}

package com.example.moviecatalogue2.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.moviecatalogue2.R
import com.example.moviecatalogue2.fragment.FavoriteFragment
import com.example.moviecatalogue2.fragment.MoviesFragment
import com.example.moviecatalogue2.fragment.TvShowFragment

class FragmentAdapter(fm: FragmentManager, private val context: Context) :
    FragmentPagerAdapter(fm) {
    private val pages = listOf(
        MoviesFragment(),
        TvShowFragment(),
        FavoriteFragment()
    )

    override fun getItem(p0: Int): Fragment {
        return pages[p0]
    }

    override fun getCount(): Int {
        return pages.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> context.getString(R.string.film)
            1 -> context.getString(R.string.tvShow)
            2 -> context.getString(R.string.favorite)
            else -> ""
        }
    }

}
package com.example.moviecatalogue2.adapter

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.moviecatalogue2.R
import com.example.moviecatalogue2.fragment.MoviesFragment
import com.example.moviecatalogue2.fragment.TvShowFragment

class FragmentAdapter(fm: FragmentManager, private val context: Context) :
    FragmentPagerAdapter(fm) {
    private val pages = listOf(
        MoviesFragment(),
        TvShowFragment()
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
            else -> ""
        }
    }

}
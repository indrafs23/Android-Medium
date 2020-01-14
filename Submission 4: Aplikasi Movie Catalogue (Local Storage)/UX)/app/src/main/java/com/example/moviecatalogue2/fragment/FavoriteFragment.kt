package com.example.moviecatalogue2.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.moviecatalogue2.R
import com.example.moviecatalogue2.adapter.FragmentFavoriteAdapter
import kotlinx.android.synthetic.main.fragment_favorite.*
import kotlinx.android.synthetic.main.fragment_favorite.view.*

/**
 * A simple [Fragment] subclass.
 */
class FavoriteFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_favorite, container, false)
        view.viewFrangmentMovies.adapter = activity?.let { FragmentFavoriteAdapter(childFragmentManager, it) }
        view.tabs_main_favorite.setupWithViewPager(viewFrangmentMovies)

        return view
    }


}

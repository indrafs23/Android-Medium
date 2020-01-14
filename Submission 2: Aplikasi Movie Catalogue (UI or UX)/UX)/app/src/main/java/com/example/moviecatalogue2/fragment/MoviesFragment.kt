package com.example.moviecatalogue2.fragment


import Film
import FilmData
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.moviecatalogue2.R
import com.example.moviecatalogue2.adapter.ListFilmAdapter
import com.example.moviecatalogue2.main.DetailFilmActivity
import kotlinx.android.synthetic.main.fragment_movies.*

/**
 * A simple [Fragment] subclass.
 */
class MoviesFragment : Fragment() {
    private var list: ArrayList<Film> = arrayListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        moviesRecyclerView.setHasFixedSize(true)

        list.addAll(FilmData.listData)

        moviesRecyclerView.apply {
            moviesRecyclerView.layoutManager = LinearLayoutManager(activity)
            val listFilmAdapter = ListFilmAdapter(list)
            moviesRecyclerView.adapter = listFilmAdapter

            listFilmAdapter.setOnItemClickCallback(object : ListFilmAdapter.OnItemClickCallback{
                override fun onItemClicked(data: Film) {
                    val moveDetail = Intent(getActivity(), DetailFilmActivity::class.java)
                    moveDetail.putExtra("data", data as Parcelable)
                    startActivity(moveDetail)
                }
            })
        }
    }
}

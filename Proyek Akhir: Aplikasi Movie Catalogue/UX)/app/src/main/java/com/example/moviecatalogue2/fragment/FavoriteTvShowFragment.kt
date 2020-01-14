package com.example.moviecatalogue2.fragment


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviecatalogue2.R
import com.example.moviecatalogue2.adapter.ListFavoriteTvShowAdapter
import com.example.moviecatalogue2.data.TvShow
import com.example.moviecatalogue2.database.TvShowDatabase
import com.example.moviecatalogue2.main.DetailTvShowActivity
import kotlinx.android.synthetic.main.fragment_favorite_tv_show.*

/**
 * A simple [Fragment] subclass.
 */
class FavoriteTvShowFragment : Fragment() {
    private var tvShow: TvShowDatabase? = null
    private val listTvShowAdapter = ListFavoriteTvShowAdapter()
    private var listTvShow: ArrayList<TvShow>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_tv_show, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvShowFavoriteRecyclerView.setHasFixedSize(true)

        framelayout_tvShow_favorite.visibility = View.VISIBLE
        progressBar_tvShow_favorite.visibility = View.VISIBLE

        getCurrentData()

        tvShowFavoriteRecyclerView.apply {
            framelayout_tvShow_favorite.visibility = View.GONE
            progressBar_tvShow_favorite.visibility = View.GONE

            tvShowFavoriteRecyclerView.layoutManager = LinearLayoutManager(activity)
            tvShowFavoriteRecyclerView.adapter = listTvShowAdapter
            (tvShowFavoriteRecyclerView.adapter as ListFavoriteTvShowAdapter).notifyDataSetChanged()

            listTvShowAdapter.setOnItemClickCallback(object :
                ListFavoriteTvShowAdapter.OnItemClickCallback {
                override fun onItemClicked(data: TvShow) {
                    val moveDetail = Intent(getActivity(), DetailTvShowActivity::class.java)
                    val bundle = Bundle()
                    bundle.putParcelable("bundle",data)
                    moveDetail.putExtra("myBundle", bundle)
                    startActivity(moveDetail)
                }
            })
        }
    }

    private fun getCurrentData() {
        tvShow = activity?.let { TvShowDatabase.getInstance(it) }
        listTvShow = tvShow?.TvShowDao()?.getAllTvShow() as ArrayList<TvShow>?

        if (listTvShow != null) {
            listTvShowAdapter.setNewDataTvShow(listTvShow!!)
        } else {
            framelayout_tvShow_favorite.visibility = View.GONE
            framelayout_tvShow_favorite.visibility = View.GONE
        }
    }

}

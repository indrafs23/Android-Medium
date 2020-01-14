package com.example.moviecatalogue2.fragment


import TvShowData
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.moviecatalogue2.R
import com.example.moviecatalogue2.adapter.ListTvShowAdapter
import com.example.moviecatalogue2.data.TvShow
import com.example.moviecatalogue2.main.DetailTvShowActivity
import kotlinx.android.synthetic.main.fragment_tv_show.*

/**
 * A simple [Fragment] subclass.
 */
class TvShowFragment : Fragment() {
    private var list: ArrayList<TvShow> = arrayListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv_show, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvShowRecyclerView.setHasFixedSize(true)

        list.addAll(TvShowData.listData)

        tvShowRecyclerView.apply {
            tvShowRecyclerView.layoutManager = LinearLayoutManager(activity)
            val listTvShowAdapter = ListTvShowAdapter(list)
            tvShowRecyclerView.adapter = listTvShowAdapter

            listTvShowAdapter.setOnItemClickCallback(object : ListTvShowAdapter.OnItemClickCallback {
                override fun onItemClicked(data: TvShow) {
                    val moveDetail = Intent(getActivity(), DetailTvShowActivity::class.java)
                    moveDetail.putExtra("data", data as Parcelable)
                    startActivity(moveDetail)

                }
            })
        }
    }

}

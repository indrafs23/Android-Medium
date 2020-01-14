package com.example.moviecatalogue2.fragment


import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.view.*
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviecatalogue2.R
import com.example.moviecatalogue2.adapter.ListTvShowAdapter
import com.example.moviecatalogue2.data.ListTvShow
import com.example.moviecatalogue2.data.TmdbApi
import com.example.moviecatalogue2.data.TvShow
import com.example.moviecatalogue2.main.DetailTvShowActivity
import kotlinx.android.synthetic.main.fragment_tv_show.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * A simple [Fragment] subclass.
 */
open class TvShowFragment : Fragment() {
    private val listTvShowAdapter = ListTvShowAdapter()
    private var listTvShow: ArrayList<TvShow>? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_tv_show, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        framelayout_fragment_tvShow.visibility = View.VISIBLE
        progressBar_fragment_tvShow.visibility = View.VISIBLE

        tvShowRecyclerView.setHasFixedSize(true)
        getCurrentData()

        tvShowRecyclerView.apply {
            tvShowRecyclerView.layoutManager = LinearLayoutManager(activity)
            tvShowRecyclerView.adapter = listTvShowAdapter

            listTvShowAdapter.setOnItemClickCallback(object :
                ListTvShowAdapter.OnItemClickCallback {
                override fun onItemClicked(data: TvShow) {
                    val moveDetail = Intent(getActivity(), DetailTvShowActivity::class.java)
                    moveDetail.putExtra("data", data as Parcelable)
                    val bundle = Bundle()
                    bundle.putParcelable("bundle",data)
                    moveDetail.putExtra("myBundle", bundle)
                    startActivity(moveDetail)
                }
            })
        }

        if (savedInstanceState == null) {
            getCurrentData()
        } else {
            framelayout_fragment_tvShow.visibility = View.GONE
            progressBar_fragment_tvShow.visibility = View.GONE
            listTvShow = savedInstanceState.getParcelableArrayList("tvShow")
            listTvShow?.let { listTvShowAdapter.setNewDataTvShow(it) }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putParcelableArrayList("tvShow", listTvShow)
        super.onSaveInstanceState(outState)
    }

    private fun getCurrentData() {
        val retrofit = Retrofit.Builder()
            .baseUrl(TmdbApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val movieApi = retrofit.create(TmdbApi::class.java)
        val call = movieApi.getTvShow(TmdbApi.API_KEY, TmdbApi.LANGUAGE)

        call.enqueue(object : Callback<ListTvShow> {
            override fun onFailure(call: Call<ListTvShow>, t: Throwable) {
                val toast = Toast.makeText(
                    activity,
                    getString(R.string.connection),
                    Toast.LENGTH_SHORT
                )
                toast.show()
            }

            override fun onResponse(call: Call<ListTvShow>, response: Response<ListTvShow>) {
                if (response.code() == 200) {
                    val filmResponse = response.body() as ListTvShow
                    listTvShow = ArrayList(filmResponse.results)
                    listTvShowAdapter.setNewDataTvShow(filmResponse.results)

                    if (framelayout_fragment_tvShow != null && progressBar_fragment_tvShow != null) {
                        framelayout_fragment_tvShow.visibility = View.GONE
                        progressBar_fragment_tvShow.visibility = View.GONE
                    }
                }
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
            inflater.inflate(R.menu.menu_detail_tvshow, menu)
            val searchItem = menu.findItem(R.id.search_tvShow)
            val searchView = searchItem?.actionView as SearchView
            searchView.isSubmitButtonEnabled = true

            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    query?.let { listTvShowAdapter.filterList(it) }
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    newText?.let { listTvShowAdapter.filterList(it) }
                    return true
                }

            })
            inflater.let { super.onCreateOptionsMenu(menu, it) }
    }
}

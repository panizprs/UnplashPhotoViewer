package com.acm.workshop.unplashphotoviewer.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.acm.workshop.unplashphotoviewer.R
import dagger.android.support.DaggerFragment
import javax.inject.Inject





class HomeFragment : DaggerFragment() {

    @Inject
    lateinit var homeViewModelFactory: HomeViewModelFactory

    private val homeViewModel: HomeViewModel by lazy {
        ViewModelProviders.of(this, homeViewModelFactory).get(HomeViewModel::class.java)
    }


    var recyclerView: RecyclerView? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.home_frgament, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val loadingBar = view?.findViewById<View>(R.id.loadingBar)
        recyclerView = view?.findViewById(R.id.recyclerView)
        recyclerView?.layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)

        val homeAdapter = HomeAdapter()
        recyclerView?.adapter = homeAdapter

        var pageIndex = 1
        homeViewModel.getLatestPhotos(pageIndex)
        pageIndex++
        homeViewModel.photos.observe(this, Observer { photos ->
            loadingBar?.visibility = View.GONE
            homeAdapter.addPhotos(photos)
        })

        homeViewModel.error.observe(this, Observer { throwable ->
            Toast.makeText(context, throwable.toString(), Toast.LENGTH_LONG)
        })

//        recyclerView reaches the bottom of list
        recyclerView?.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if (!recyclerView.canScrollVertically(1)) {
                    Toast.makeText(context, "Last", Toast.LENGTH_LONG).show()
                    homeViewModel.getLatestPhotos(pageIndex)
                    pageIndex++
                    homeViewModel.photos.observe(this@HomeFragment, Observer { photos ->
                        homeAdapter.addPhotos(photos)
                    })
                    homeViewModel.error.observe(this@HomeFragment, Observer { throwable ->
                        Toast.makeText(context, throwable.toString(), Toast.LENGTH_LONG)
                    })


                }
            }
        })
    }
}
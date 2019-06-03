package com.acm.workshop.unplashphotoviewer.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.acm.workshop.unplashphotoviewer.R
import com.acm.workshop.unplashphotoviewer.app.UnplashPhotoViewerApp
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class HomeFragment : DaggerFragment(){

    @Inject
    lateinit var homeViewModelFactory : HomeViewModelFactory

    private val homeViewModel: HomeViewModel by lazy {
        ViewModelProviders.of(this, homeViewModelFactory).get(HomeViewModel::class.java)
    }




    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.home_frgament, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val loadingBar = view?.findViewById<View>(R.id.loadingBar)
        val recyclerView = view?.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView?.layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)


        homeViewModel.getLatestPhotos()
        homeViewModel.photos.observe(this, Observer {photos ->
            loadingBar?.visibility = View.GONE
            recyclerView?.adapter = HomeAdapter(photos)
        })

        homeViewModel.error.observe(this, Observer {throwable ->
            Toast.makeText(context, throwable.toString(), Toast.LENGTH_LONG)
        })
    }
}
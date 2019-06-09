package com.acm.workshop.unplashphotoviewer.ui.home

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.acm.workshop.unplashphotoviewer.R
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.home_frgament.*
import java.util.zip.Inflater
import javax.inject.Inject






class HomeFragment : DaggerFragment() {

    @Inject
    lateinit var homeViewModelFactory: HomeViewModelFactory

    private val homeViewModel: HomeViewModel by lazy {
        ViewModelProviders.of(this, homeViewModelFactory).get(HomeViewModel::class.java)
    }


    var recyclerView: RecyclerView? = null

    var orderBy = "latest"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }


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
        loadPhotos(1, homeAdapter, orderBy)

//        recyclerView reaches the bottom of list
        recyclerView?.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1)) {
                    Toast.makeText(context, "Last", Toast.LENGTH_LONG).show()
                    pageIndex++
                    loadPhotos(pageIndex, homeAdapter, orderBy)
                }
            }
        })
    }

    fun loadPhotos(pageIndex : Int , homeAdapter : HomeAdapter, orderBy : String){
        homeViewModel.getLatestPhotos(pageIndex, orderBy, context)
        homeViewModel.photos.observe(this, Observer { photos ->
            loadingBar?.visibility = View.GONE
            homeAdapter.addPhotos(photos)
        })

        homeViewModel.error.observe(this, Observer { throwable ->
            println("error $throwable")
            val builder = AlertDialog.Builder(context)
            builder.setTitle("Error")
            builder.setMessage(throwable.message)


            builder.setPositiveButton("retry", { dialog, which ->
                dialog.dismiss()
                homeViewModel.getLatestPhotos(pageIndex, orderBy, context)
            })

            val dialog = builder.create()
            dialog.show()
            Toast.makeText(context, throwable.toString(), Toast.LENGTH_LONG).show()
        })

    }


    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.home_menu_toolbar, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.latestPhotos -> {
                orderBy = "latest"
                loadPhotos(1, HomeAdapter(), orderBy)
            }
            R.id.oldestPhotos -> {
                orderBy = "oldest"
                loadPhotos(1, HomeAdapter(), orderBy)
            }
            R.id.popularPhotos ->{
                orderBy = "popular"
                loadPhotos(1, HomeAdapter(), orderBy)
            }
        }
        return super.onOptionsItemSelected(item)
    }

}
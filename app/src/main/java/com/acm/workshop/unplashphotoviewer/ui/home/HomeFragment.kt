package com.acm.workshop.unplashphotoviewer.ui.home

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.acm.workshop.UnsplashPhotoViewer.domain.model.Photo
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.recyclerview_list.*
import javax.inject.Inject
import com.acm.workshop.remote.api.PhotoApi.Companion.ORDER_BY_LATEST
import com.acm.workshop.remote.api.PhotoApi.Companion.ORDER_BY_OLDEST
import com.acm.workshop.remote.api.PhotoApi.Companion.ORDER_BY_POPULAR
import com.acm.workshop.unplashphotoviewer.R
import com.acm.workshop.unplashphotoviewer.ui.detail.DetailFragment


class HomeFragment : DaggerFragment(), OnPhotoClickListener {


    @Inject
    lateinit var homeViewModelFactory: HomeViewModelFactory

    private val homeViewModel: HomeViewModel by lazy {
        ViewModelProviders.of(this, homeViewModelFactory).get(HomeViewModel::class.java)
    }


    lateinit var orderBy : String

    var pageIndex : Int = 1

    var recyclerView: RecyclerView? = null
    var currentPosition: Int? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        println("onCreate")
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

        savedInstanceState?.let { savedState ->
            pageIndex = savedState.getInt(KEY_PAGE_INDEX)
            currentPosition = savedState.getInt(KEY_LAST_POSITION)
            orderBy = savedState.getString(KEY_ORDER_BY)
            println("onRestore: $currentPosition")
            println("onRestore: $pageIndex")
            println("orderBY: $orderBy" )
        }.run {
            pageIndex = 1
            currentPosition = 0
            orderBy = "latest"

        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        println("onCreateView")
        return inflater.inflate(R.layout.recyclerview_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        println("onViewCreated")



//        val staggeredGridLayoutManager : StaggeredGridLayoutManager = if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
//            StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
//        } else {
//            StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL)
//        }

        val staggeredGridLayoutManager = StaggeredGridLayoutManager(resources.getInteger(R.integer.photos_list_columns), StaggeredGridLayoutManager.VERTICAL)

        recyclerView = view.findViewById(R.id.recyclerView)

        currentPosition?.let { position ->
            println("onSetPos: $position")
            staggeredGridLayoutManager.scrollToPosition(position)
        }

        val homeAdapter = HomeAdapter(this)
        recyclerView?.adapter = homeAdapter
        recyclerView?.layoutManager = staggeredGridLayoutManager


        println("pageIndex $pageIndex")
        loadPhotos(pageIndex, homeAdapter, orderBy)

//        recyclerView reaches the bottom of list
        recyclerView?.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                currentPosition = (recyclerView.layoutManager as StaggeredGridLayoutManager)
                                    .findLastCompletelyVisibleItemPositions(null)[0]
//                    .spanCount
                currentPosition?.let { pos -> println("pos $pos") }

                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1)) {
                    Toast.makeText(context, "Last", Toast.LENGTH_LONG).show()
                    pageIndex++
                    println(pageIndex)
                    loadPhotos(pageIndex, homeAdapter, orderBy)
                }
            }
        })

    }

    fun loadPhotos(pageIndex: Int, homeAdapter: HomeAdapter, orderBy: String) {
        println("pageIndex: $pageIndex")

        homeViewModel.getPhotos(pageIndex,resources.getInteger(R.integer.photos_num_load_each_time), orderBy, context)

//        if (pageIndex == 1)
//            recyclerView?.scrollToPosition(0)

        homeViewModel.photos.observe(this, Observer { photos ->
            loadingBar?.visibility = View.GONE
            println("observe $pageIndex")
            if (pageIndex == 1) {
                println("update photos")
                homeAdapter.updatePhotos(photos)
            } else
                homeAdapter.addPhotos(photos)
        })

        homeViewModel.error.observe(this, Observer { throwable ->
            println("error $throwable")
            val builder = AlertDialog.Builder(context)
            builder.setTitle("Error")
            builder.setMessage(throwable.message)


            builder.setPositiveButton("retry") { dialog, which ->
                dialog.dismiss()
                homeViewModel.getPhotos(pageIndex,resources.getInteger(R.integer.photos_num_load_each_time), orderBy, context)
            }

            val dialog = builder.create()
            dialog.show()
            Toast.makeText(context, throwable.toString(), Toast.LENGTH_LONG).show()
        })

    }


    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.home_menu_toolbar, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.latestPhotos -> {
                orderBy = ORDER_BY_LATEST
                loadPhotos(1, HomeAdapter(this), orderBy)
            }
            R.id.oldestPhotos -> {
                orderBy = ORDER_BY_OLDEST
                loadPhotos(1, HomeAdapter(this), orderBy)
            }
            R.id.popularPhotos -> {
                orderBy = ORDER_BY_POPULAR
                loadPhotos(1, HomeAdapter(this), orderBy)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        println("onSaveInstanceState")
        currentPosition?.let { position ->
            outState.putInt(KEY_LAST_POSITION, position)
            outState.putInt(KEY_PAGE_INDEX, pageIndex)
            outState.putString(KEY_ORDER_BY, orderBy)
            println("onSave: $position")
        }
        super.onSaveInstanceState(outState)
    }

    override fun onPhotoClicked(photo : Photo) {
        fragmentManager?.beginTransaction()
            ?.replace(R.id.content_frame, DetailFragment.newInstance(photo))
            ?.addToBackStack(null)
            ?.commit()
    }


    companion object {
        const val KEY_LAST_POSITION = "KEY_PHOTO_LIST_LAST_POSITION"
        const val KEY_PAGE_INDEX = "KEY_PHOTO_PAGE_INDEX"
        const val KEY_ORDER_BY = "KEY_PHOTO_ORDER_BY"
    }

}
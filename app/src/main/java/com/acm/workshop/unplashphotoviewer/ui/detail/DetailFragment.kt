package com.acm.workshop.unplashphotoviewer.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.acm.workshop.UnplashPhotoViewer.domain.model.Photo
import com.acm.workshop.unplashphotoviewer.R
import dagger.android.support.DaggerFragment

class DetailFragment : DaggerFragment(){

    private var photo : Photo? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.recyclerview_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        photo?.let {notNullPhoto ->
            println(notNullPhoto.id)
            println(notNullPhoto.regular)
            println(notNullPhoto.description)

            view?.findViewById<View>(R.id.loadingBar)?.visibility = View.GONE


            val recyclerView = view?.findViewById<RecyclerView>(R.id.recyclerView)
            recyclerView?.layoutManager = LinearLayoutManager(context)
            recyclerView?.adapter = DetailAdapter(notNullPhoto)
        }
    }


    companion object {
        fun newInstance(photoClicked: Photo) : DetailFragment{
            return DetailFragment().apply {
                photo = photoClicked
            }
        }

    }
}
package com.acm.workshop.unplashphotoviewer.ui.detail

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.acm.workshop.UnsplashPhotoViewer.domain.model.Photo
import dagger.android.support.DaggerFragment
import javax.inject.Inject
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import com.acm.workshop.unplashphotoviewer.R


class DetailFragment : DaggerFragment(), OnDownloadPhotoClickListener, OnPhotoLoaded {

    private var photo: Photo? = null

    @Inject
    lateinit var detailViewModelFactory: DetailViewModelFactory

    private val detailViewModel: DetailViewModel by lazy {
        ViewModelProviders.of(this, detailViewModelFactory)[DetailViewModel::class.java]
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.recyclerview_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        photo?.let { notNullPhoto ->
            println(notNullPhoto.id)
            println(notNullPhoto.regular)
            println(notNullPhoto.description)


            val recyclerView = view?.findViewById<RecyclerView>(R.id.recyclerView)
            recyclerView?.layoutManager = LinearLayoutManager(context)
            recyclerView?.adapter = DetailAdapter(notNullPhoto, this, this)

        }
    }

    override fun onDownloadIconClicked() {
        if (ActivityCompat.checkSelfPermission(requireContext(), android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissions(
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE
            )

        } else {
            saveImage()
        }

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    saveImage()
                }
                return
            }
        }
    }


    private fun saveImage() {
        detailViewModel.savePhotoFile(photo?.full, photo?.id)
        detailViewModel.result.observe(this, Observer { result ->
            Toast.makeText(requireContext(), result, Toast.LENGTH_LONG).show()
            println("save image")
            println("${photo?.id}.jpg")
        })
    }

    override fun onPhotoLoaded() {
        view?.findViewById<View>(R.id.loadingBar)?.visibility = View.GONE
    }

    companion object {
        fun newInstance(photoClicked: Photo): DetailFragment {
            return DetailFragment().apply {
                photo = photoClicked
            }
        }

        const val PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 112

    }
}


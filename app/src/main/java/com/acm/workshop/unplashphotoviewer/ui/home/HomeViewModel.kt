package com.acm.workshop.unplashphotoviewer.ui.home


import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.acm.workshop.UnplashPhotoViewer.domain.interactor.GetPhotosUseCase
import com.acm.workshop.UnplashPhotoViewer.domain.model.Photo
import com.acm.workshop.unplashphotoviewer.di.module.NetworkModule

class HomeViewModel(private val getLatestPhotoUseCase : GetPhotosUseCase) : ViewModel(){

    private var prvOrderBy : String = ""

    private val _photos = MutableLiveData<List<Photo>>()
    val photos : LiveData<List<Photo>> = _photos

    private val _error = MutableLiveData<Throwable>()
    val error : LiveData<Throwable> = _error

    fun getPhotos(page: Int, orderBy: String, context : Context?){
//        println(page)
        if(NetworkModule.isNetworkConnected(context)) {
            if(prvOrderBy != orderBy || page > 1){
                getLatestPhotoUseCase.execute(GetPhotosUseCase.Param(page, 24, orderBy), ::success, ::fail)
                prvOrderBy = orderBy
            }
        }
        else
            _error.value = Throwable("No Internet Connection")

    }

    private fun success(photos : List<Photo>){
        println("onSuccess ${photos.size}")
        _photos.value = photos
    }

    private fun fail(throwable: Throwable){
        _error.value = throwable
    }



}
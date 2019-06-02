package com.acm.workshop.unplashphotoviewer.ui.home


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.acm.workshop.UnplashPhotoViewer.domain.interactor.GetLatestPhotosUseCase
import com.acm.workshop.UnplashPhotoViewer.domain.model.Photo

class HomeViewModel(private val getLatestPhotoUseCase : GetLatestPhotosUseCase) : ViewModel(){

    private val _photos = MutableLiveData<List<Photo>>()
    val photos : LiveData<List<Photo>> = _photos

    private val _error = MutableLiveData<Throwable>()
    val error : LiveData<Throwable> = _error

    fun getLatestPhotos(){
        getLatestPhotoUseCase.execute(GetLatestPhotosUseCase.None(), ::success, ::fail)
    }

    private fun success(photos : List<Photo>){
        println("onSuccess ${photos.size}")
        _photos.value = photos
    }

    private fun fail(throwable: Throwable){
        _error.value = throwable
    }



}
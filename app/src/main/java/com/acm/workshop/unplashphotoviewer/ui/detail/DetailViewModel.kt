package com.acm.workshop.unplashphotoviewer.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.acm.workshop.UnsplashPhotoViewer.domain.interactor.SaveFileUseCase

class DetailViewModel(private val saveFileUseCase: SaveFileUseCase) : ViewModel() {

    private val _result = MutableLiveData<String>()
    val result : LiveData<String> = _result


    fun savePhotoFile(url: String?, fileName: String?) {
        if(url != null && fileName != null){
            saveFileUseCase.execute(url to fileName, ::successSaveImage)
        }

    }

    private fun successSaveImage(){
        _result.value = "image saved successfully"
    }


}
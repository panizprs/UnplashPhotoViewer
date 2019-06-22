package com.acm.workshop.unplashphotoviewer.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.acm.workshop.UnsplashPhotoViewer.data.datasource.PhotoLocalDataSource
import com.acm.workshop.UnsplashPhotoViewer.domain.interactor.SaveFileUseCase
import javax.inject.Inject

class DetailViewModelFactory @Inject constructor(private val saveFileUseCase: SaveFileUseCase) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DetailViewModel(saveFileUseCase) as T
    }

}
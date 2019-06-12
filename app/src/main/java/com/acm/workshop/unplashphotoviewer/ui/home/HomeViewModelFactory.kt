package com.acm.workshop.unplashphotoviewer.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.acm.workshop.UnplashPhotoViewer.domain.interactor.GetPhotosUseCase
import javax.inject.Inject


class HomeViewModelFactory @Inject constructor(private val getLatestPhotosUseCase: GetPhotosUseCase) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeViewModel(getLatestPhotosUseCase) as T
    }

}
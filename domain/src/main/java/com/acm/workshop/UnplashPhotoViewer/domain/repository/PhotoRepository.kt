package com.acm.workshop.UnplashPhotoViewer.domain.repository


import com.acm.workshop.UnplashPhotoViewer.domain.model.Photo
import io.reactivex.Single

interface PhotoRepository {
    fun getLatest() : Single<List<Photo>>

}
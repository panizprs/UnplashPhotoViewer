package com.acm.workshop.UnsplashPhotoViewer.domain.repository


import com.acm.workshop.UnsplashPhotoViewer.domain.model.Photo
import io.reactivex.Single

interface PhotoRepository {
    fun getPhotos(page: Int, pageSize: Int, orderBy: String) : Single<List<Photo>>

    fun saveImageFile(url: String?, fileName: String)

}
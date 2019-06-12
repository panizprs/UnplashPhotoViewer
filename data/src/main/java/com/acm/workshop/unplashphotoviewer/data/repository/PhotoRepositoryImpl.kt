package com.acm.workshop.UnplashphotoViewer.data.repository

import com.acm.workshop.UnplashPhotoViewer.domain.model.Photo
import com.acm.workshop.UnplashPhotoViewer.domain.repository.PhotoRepository
import com.acm.workshop.UnplashphotoViewer.data.datasource.PhotoRemoteDataSource
import com.acm.workshop.UnplashphotoViewer.data.entity.toPhoto
import io.reactivex.Single


class PhotoRepositoryImpl(private val photoRemoteDataSource: PhotoRemoteDataSource) : PhotoRepository {
    override fun getPhotos(page: Int, pageSize: Int, orderBy: String): Single<List<Photo>> {
        return photoRemoteDataSource.getPhotos(page, pageSize, orderBy).map { photoEntities ->
            photoEntities.map { photoEntity ->
                photoEntity.toPhoto()
            }
        }
    }
}



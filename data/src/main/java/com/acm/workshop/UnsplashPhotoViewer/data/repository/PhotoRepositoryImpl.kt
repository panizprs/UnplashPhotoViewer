package com.acm.workshop.UnplashphotoViewer.data.repository

import com.acm.workshop.UnsplashPhotoViewer.domain.model.Photo
import com.acm.workshop.UnsplashPhotoViewer.domain.repository.PhotoRepository
import com.acm.workshop.UnplashphotoViewer.data.datasource.PhotoRemoteDataSource
import com.acm.workshop.UnplashphotoViewer.data.entity.toPhoto
import com.acm.workshop.UnsplashPhotoViewer.data.datasource.PhotoLocalDataSource
import io.reactivex.Single


class PhotoRepositoryImpl(
    private val photoRemoteDataSource: PhotoRemoteDataSource,
    private val photoLocalDataSource: PhotoLocalDataSource
    ) : PhotoRepository {

    override fun getPhotos(page: Int, pageSize: Int, orderBy: String): Single<List<Photo>> {
        return photoRemoteDataSource.getPhotos(page, pageSize, orderBy).map { photoEntities ->
            photoEntities.map { photoEntity ->
                photoEntity.toPhoto()
            }
        }
    }


    override fun saveImageFile(url: String?, fileName: String) {
        photoLocalDataSource.saveImageFile(url, fileName)
    }

}



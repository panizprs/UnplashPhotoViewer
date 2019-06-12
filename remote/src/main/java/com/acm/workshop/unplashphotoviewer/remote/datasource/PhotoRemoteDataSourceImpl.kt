package com.acm.workshop.remote.datasource

import com.acm.workshop.UnplashphotoViewer.data.datasource.PhotoRemoteDataSource
import com.acm.workshop.UnplashphotoViewer.data.entity.PhotoEntity
import com.acm.workshop.remote.api.PhotoApi
import com.acm.workshop.remote.dto.toPhotoEntity
import io.reactivex.Single

class PhotoRemoteDataSourceImpl(private val photoApi: PhotoApi) : PhotoRemoteDataSource {
    override fun getPhotos(page: Int, pageSize: Int, orderBy: String): Single<List<PhotoEntity>> {

        return photoApi.getPhotos("f49cf4050a7f98026dc1e6cec7033e151d936de8435faba8e637aa0d10cc6bd1", page, pageSize, orderBy).map { response ->
//        return photoApi.getPhotos( 1, 10).map { reponse ->
            response.body()?.map {unsplashPhoto ->
                unsplashPhoto.toPhotoEntity()
            }
        }
    }
}
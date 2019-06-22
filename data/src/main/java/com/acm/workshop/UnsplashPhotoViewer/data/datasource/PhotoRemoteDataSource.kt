package com.acm.workshop.UnplashphotoViewer.data.datasource


import com.acm.workshop.UnplashphotoViewer.data.entity.PhotoEntity
import io.reactivex.Observable
import io.reactivex.Single

interface PhotoRemoteDataSource{
    fun getPhotos(page: Int, pageSize: Int, orderBy: String): Single<List<PhotoEntity>>
}
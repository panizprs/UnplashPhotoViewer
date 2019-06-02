package com.acm.workshop.unplashphotoviewer.di.module

import com.acm.workshop.UnplashPhotoViewer.domain.repository.PhotoRepository
import com.acm.workshop.UnplashphotoViewer.data.datasource.PhotoRemoteDataSource
import com.acm.workshop.UnplashphotoViewer.data.repository.PhotoRepositoryImpl
import com.acm.workshop.remote.api.PhotoApi
import com.acm.workshop.remote.datasource.PhotoRemoteDataSourceImpl
import dagger.Module
import dagger.Provides

@Module
class HomeModule{

    @Provides
    fun providePhotoRemoteDataSource(photoApi: PhotoApi) : PhotoRemoteDataSource {
        return PhotoRemoteDataSourceImpl(photoApi)
    }

    @Provides
    fun providePhotoRepository(photoRemoteDataSource : PhotoRemoteDataSource) : PhotoRepository{
        return PhotoRepositoryImpl(photoRemoteDataSource)
    }
}
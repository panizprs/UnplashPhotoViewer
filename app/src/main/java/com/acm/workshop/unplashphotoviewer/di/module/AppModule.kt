package com.acm.workshop.unplashphotoviewer.di.module

import com.acm.workshop.UnsplashPhotoViewer.domain.executor.PostExecutorThread
import com.acm.workshop.UnsplashPhotoViewer.domain.executor.UseCaseExecutorThread
import com.acm.workshop.unplashphotoviewer.app.UnplashPhotoViewerApp
import com.acm.workshop.unplashphotoviewer.executor.BackgroundThread
import com.acm.workshop.unplashphotoviewer.executor.MainThread
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    fun provideContext(app: UnplashPhotoViewerApp) = app.applicationContext


    @Provides
    fun provideMainThread() : PostExecutorThread{
        return MainThread()
    }

    @Provides
    fun provideBackgroundThread() : UseCaseExecutorThread{
        return BackgroundThread()
    }
}
package com.acm.workshop.unplashphotoviewer.app

import android.app.Application
import com.acm.workshop.unplashphotoviewer.di.component.AppComponent
import com.acm.workshop.unplashphotoviewer.di.component.DaggerAppComponent
import com.acm.workshop.unplashphotoviewer.di.module.AppModule
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class UnplashPhotoViewerApp : DaggerApplication(){
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.factory().create(this)
    }

}
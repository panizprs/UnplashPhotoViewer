package com.acm.workshop.unplashphotoviewer.app


import com.acm.workshop.unplashphotoviewer.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class UnplashPhotoViewerApp : DaggerApplication(){
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.factory().create(this)
    }

}

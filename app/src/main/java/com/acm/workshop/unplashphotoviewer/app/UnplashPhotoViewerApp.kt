package com.acm.workshop.unplashphotoviewer.app

import android.app.Application
import com.acm.workshop.unplashphotoviewer.di.component.AppComponent
import com.acm.workshop.unplashphotoviewer.di.component.DaggerAppComponent
import com.acm.workshop.unplashphotoviewer.di.module.AppModule

class UnplashPhotoViewerApp : Application(){

    override fun onCreate() {
        super.onCreate()

        component = DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }

    companion object {

        lateinit var component : AppComponent
    }
}
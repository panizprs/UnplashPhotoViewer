package com.acm.workshop.unplashphotoviewer.di.component

import com.acm.workshop.unplashphotoviewer.app.UnplashPhotoViewerApp
import com.acm.workshop.unplashphotoviewer.di.module.AppModule
import com.acm.workshop.unplashphotoviewer.di.module.FragmentsModule
import com.acm.workshop.unplashphotoviewer.di.module.HomeModule
import com.acm.workshop.unplashphotoviewer.di.module.NetworkModule
import com.acm.workshop.unplashphotoviewer.ui.home.HomeFragment
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector

@Component(
    modules = [
        AndroidInjectionModule::class,
        FragmentsModule::class,
        AppModule::class,
        NetworkModule::class,
        HomeModule::class]
)
interface AppComponent : AndroidInjector<UnplashPhotoViewerApp>{

    @Component.Factory
    abstract class Factory : AndroidInjector.Factory<UnplashPhotoViewerApp>

}
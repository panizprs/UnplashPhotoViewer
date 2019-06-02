package com.acm.workshop.unplashphotoviewer.di.component

import com.acm.workshop.unplashphotoviewer.di.module.AppModule
import com.acm.workshop.unplashphotoviewer.di.module.HomeModule
import com.acm.workshop.unplashphotoviewer.di.module.NetworkModule
import com.acm.workshop.unplashphotoviewer.ui.home.HomeFragment
import dagger.Component

@Component(
    modules = [
        AppModule::class,
        NetworkModule::class,
        HomeModule::class]
)
interface AppComponent {

    fun inject(homeFragment: HomeFragment)
}
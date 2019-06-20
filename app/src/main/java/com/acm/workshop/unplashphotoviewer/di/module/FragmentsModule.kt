package com.acm.workshop.unplashphotoviewer.di.module

import com.acm.workshop.unplashphotoviewer.ui.detail.DetailFragment
import com.acm.workshop.unplashphotoviewer.ui.home.HomeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentsModule{

    @ContributesAndroidInjector
    abstract fun homeFragment() : HomeFragment

    @ContributesAndroidInjector
    abstract fun datailFragment() : DetailFragment
}
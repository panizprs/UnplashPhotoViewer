package com.acm.workshop.UnplashPhotoViewer.domain.interactor.base

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable


abstract class BaseUseClass{
    val disposables = CompositeDisposable()

    fun add(disposable: Disposable){
        disposables.add(disposable)
    }

    fun dispose(){
        if(!disposables.isDisposed){
            disposables.dispose()
        }
    }
}
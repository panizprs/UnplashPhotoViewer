package com.acm.workshop.UnsplashPhotoViewer.domain.interactor.base

import com.acm.workshop.UnsplashPhotoViewer.domain.executor.PostExecutorThread
import com.acm.workshop.UnsplashPhotoViewer.domain.executor.UseCaseExecutorThread
import io.reactivex.Completable

abstract class CompletableUseCase<in Param>(
    private val postExecutorThread: PostExecutorThread,
    private val useCaseExecutorThread: UseCaseExecutorThread
) : BaseUseClass() {

    abstract fun buildCompletable(param: Param): Completable

    fun execute(param: Param, onSuccess: () -> Unit) {
        buildCompletable(param)
            .subscribeOn(useCaseExecutorThread.scheduler)
            .observeOn(postExecutorThread.scheduler)
            .subscribe {
                onSuccess()
            }
            .also {disposable ->
                add(disposable)
            }
    }

}
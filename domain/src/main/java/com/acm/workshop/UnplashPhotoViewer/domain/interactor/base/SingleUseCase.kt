package com.acm.workshop.UnplashPhotoViewer.domain.interactor.base

import com.acm.workshop.UnplashPhotoViewer.domain.executor.UseCaseExecutorThread
import com.acm.workshop.UnplashPhotoViewer.domain.executor.PostExecutorThread
import io.reactivex.Single


abstract class SingleUseCase<in Params, Result>(
    private val postExecutorThread: PostExecutorThread,
    private val useCaseExecutorThread: UseCaseExecutorThread
) : BaseUseClass() {

    abstract fun buildSingle(params: Params): Single<Result>

    fun execute(
        params: Params,
        onSuccess: (Result) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        buildSingle(params)
            .observeOn(postExecutorThread.scheduler)
            .subscribeOn(useCaseExecutorThread.scheduler)
            .subscribe({ result ->
//                println("exec: $result")
                onSuccess(result)
            }, { error ->
                onFailure(error)
            }).also { disposable ->
                add(disposable)
            }
    }

}

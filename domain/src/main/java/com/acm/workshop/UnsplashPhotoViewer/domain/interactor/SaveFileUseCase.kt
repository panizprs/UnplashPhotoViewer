package com.acm.workshop.UnsplashPhotoViewer.domain.interactor

import com.acm.workshop.UnsplashPhotoViewer.domain.executor.PostExecutorThread
import com.acm.workshop.UnsplashPhotoViewer.domain.executor.UseCaseExecutorThread
import com.acm.workshop.UnsplashPhotoViewer.domain.interactor.base.CompletableUseCase
import com.acm.workshop.UnsplashPhotoViewer.domain.repository.PhotoRepository
import io.reactivex.Completable
import javax.inject.Inject


class SaveFileUseCase @Inject constructor(
    private val photoRepository: PhotoRepository,
    postExecutorThread: PostExecutorThread,
    useCaseExecutorThread: UseCaseExecutorThread
) : CompletableUseCase<Pair<String, String>>(postExecutorThread, useCaseExecutorThread) {


    override fun buildCompletable(paramsURLFileName : Pair<String, String>): Completable {
        return Completable.fromCallable {
            photoRepository.saveImageFile(paramsURLFileName.first, paramsURLFileName.second)
        }
    }

}
package com.acm.workshop.UnplashPhotoViewer.domain.interactor

import com.acm.workshop.UnplashPhotoViewer.domain.executor.UseCaseExecutorThread
import com.acm.workshop.UnplashPhotoViewer.domain.executor.PostExecutorThread
import com.acm.workshop.UnplashPhotoViewer.domain.interactor.base.SingleUseCase
import com.acm.workshop.UnplashPhotoViewer.domain.model.Photo
import com.acm.workshop.UnplashPhotoViewer.domain.repository.PhotoRepository
import io.reactivex.Single
import javax.inject.Inject

class GetLatestPhotosUseCase @Inject constructor(
    private val photoRepository: PhotoRepository,
    postExecutorThread: PostExecutorThread,
    backgroundExecutorThread: UseCaseExecutorThread
) : SingleUseCase<GetLatestPhotosUseCase.None, List<Photo>>(postExecutorThread, backgroundExecutorThread) {

    override fun buildSingle(params: None): Single<List<Photo>> {
        return photoRepository.getLatest()
    }

    class None
}
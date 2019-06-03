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
) : SingleUseCase<GetLatestPhotosUseCase.Param, List<Photo>>(postExecutorThread, backgroundExecutorThread) {

    override fun buildSingle(params: Param): Single<List<Photo>> {
        return photoRepository.getLatest(params.page, params.pageSize)
    }

    data class Param(
        val page: Int,
        val pageSize: Int
    )
}
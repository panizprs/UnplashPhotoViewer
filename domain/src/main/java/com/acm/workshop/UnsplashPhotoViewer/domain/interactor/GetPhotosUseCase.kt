package com.acm.workshop.UnsplashPhotoViewer.domain.interactor

import com.acm.workshop.UnsplashPhotoViewer.domain.executor.UseCaseExecutorThread
import com.acm.workshop.UnsplashPhotoViewer.domain.executor.PostExecutorThread
import com.acm.workshop.UnsplashPhotoViewer.domain.interactor.base.SingleUseCase
import com.acm.workshop.UnsplashPhotoViewer.domain.model.Photo
import com.acm.workshop.UnsplashPhotoViewer.domain.repository.PhotoRepository
import io.reactivex.Single
import javax.inject.Inject

class GetPhotosUseCase @Inject constructor(
    private val photoRepository: PhotoRepository,
    postExecutorThread: PostExecutorThread,
    backgroundExecutorThread: UseCaseExecutorThread
) : SingleUseCase<GetPhotosUseCase.Param, List<Photo>>(postExecutorThread, backgroundExecutorThread) {

    override fun buildSingle(params: Param): Single<List<Photo>> {
        return photoRepository.getPhotos(params.page, params.pageSize, params.orderBy)
    }

    data class Param(
        val page: Int,
        val pageSize: Int,
        val orderBy: String
    )
}
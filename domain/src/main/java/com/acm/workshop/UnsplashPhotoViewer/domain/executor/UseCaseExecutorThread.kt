package com.acm.workshop.UnsplashPhotoViewer.domain.executor

import io.reactivex.Scheduler

interface UseCaseExecutorThread {
    val scheduler : Scheduler
}
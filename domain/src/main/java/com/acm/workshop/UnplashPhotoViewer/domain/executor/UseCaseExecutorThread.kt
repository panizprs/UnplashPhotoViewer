package com.acm.workshop.UnplashPhotoViewer.domain.executor

import io.reactivex.Scheduler

interface UseCaseExecutorThread {
    val scheduler : Scheduler
}
package com.acm.workshop.UnplashPhotoViewer.domain.executor

import io.reactivex.Scheduler

interface PostExecutorThread {
    val scheduler : Scheduler
}
package com.acm.workshop.UnsplashPhotoViewer.domain.executor

import io.reactivex.Scheduler

interface PostExecutorThread {
    val scheduler : Scheduler
}
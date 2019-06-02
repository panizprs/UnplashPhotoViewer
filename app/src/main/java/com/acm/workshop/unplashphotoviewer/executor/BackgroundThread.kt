package com.acm.workshop.unplashphotoviewer.executor

import com.acm.workshop.UnplashPhotoViewer.domain.executor.UseCaseExecutorThread
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

class BackgroundThread : UseCaseExecutorThread{
    override val scheduler: Scheduler
        get() = Schedulers.io()

}
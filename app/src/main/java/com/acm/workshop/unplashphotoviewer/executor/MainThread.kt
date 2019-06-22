package com.acm.workshop.unplashphotoviewer.executor

import com.acm.workshop.UnsplashPhotoViewer.domain.executor.PostExecutorThread
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers

class MainThread : PostExecutorThread{
    override val scheduler: Scheduler
        get() = AndroidSchedulers.mainThread()

}
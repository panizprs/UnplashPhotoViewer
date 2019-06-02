package com.acm.workshop.unplashphotoviewer.executor

import com.acm.workshop.UnplashPhotoViewer.domain.executor.PostExecutorThread
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainThread : PostExecutorThread{
    override val scheduler: Scheduler
        get() = AndroidSchedulers.mainThread()

}
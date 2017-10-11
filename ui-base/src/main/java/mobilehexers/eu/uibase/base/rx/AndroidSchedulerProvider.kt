/*
 * Copyright (c) 2016 by Tandem Bank. All rights reserved.
 */
package mobilehexers.eu.uibase.base.rx

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import mobilehexers.eu.domain.rx.SchedulerProvider
import javax.inject.Singleton

@Singleton
class AndroidSchedulerProvider : SchedulerProvider {

    override val mainThread: Scheduler
        get() = AndroidSchedulers.mainThread()

    override val ioThread: Scheduler
        get() = Schedulers.io()

    override val computationThread: Scheduler
        get() = Schedulers.computation()

    override val newThread: Scheduler
        get() = Schedulers.newThread()
}

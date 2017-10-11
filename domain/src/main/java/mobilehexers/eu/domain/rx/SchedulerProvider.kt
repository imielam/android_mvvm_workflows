/*
 * Copyright (c) 2017.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.domain.rx

import io.reactivex.Scheduler

interface SchedulerProvider {

    val mainThread: Scheduler

    val ioThread: Scheduler

    val computationThread: Scheduler

    val newThread: Scheduler
}
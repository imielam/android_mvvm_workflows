package mobilehexers.eu.domain.rx

import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import javax.inject.Singleton

interface SchedulerProvider {

    val mainThread: Scheduler

    val ioThread: Scheduler

    val computationThread: Scheduler

    val newThread: Scheduler

}
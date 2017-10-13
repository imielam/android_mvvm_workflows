/*
 * Copyright (c) 2017.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.driversweek.repository.manager

import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import mobilehexers.eu.domain.recycler.ViewType
import mobilehexers.eu.domain.rx.SchedulerProvider
import mobilehexers.eu.driversweek.repository.list.RepositoryListItem
import javax.inject.Inject

class RepositoryManager @Inject constructor(private val schedulerProvider: SchedulerProvider) {
    fun getRepositoryList(): Flowable<List<ViewType>> {
        val observable = Observable.create<List<ViewType>> { emitter: ObservableEmitter<List<ViewType>> ->
            emitter.onNext(createMockRepositoryList())
            emitter.onComplete()
        }
        return observable.toFlowable(BackpressureStrategy.BUFFER).observeOn(schedulerProvider.mainThread).subscribeOn(schedulerProvider.ioThread)
    }

    private fun createMockRepositoryList() = (1..10).map { RepositoryListItem("Github Repository: " + it) }
}
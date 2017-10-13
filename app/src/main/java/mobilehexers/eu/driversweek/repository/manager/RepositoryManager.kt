/*
 * Copyright (c) 2017.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.driversweek.repository.manager

import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import mobilehexers.eu.data.base.retrofit.RestAPI
import mobilehexers.eu.domain.recycler.ViewType
import mobilehexers.eu.domain.rx.SchedulerProvider
import mobilehexers.eu.driversweek.repository.list.RepositoryListItem
import javax.inject.Inject

class RepositoryManager @Inject constructor(private val schedulerProvider: SchedulerProvider, private val api: RestAPI) {
    fun getRepositoryList(): Observable<List<ViewType>> {

        val observable = Observable.create<List<ViewType>> { emitter: ObservableEmitter<List<ViewType>> ->
            val callResponse = api.getRepositoryList("square")
            val response = callResponse.execute()
            if (response.isSuccessful) {
                val repositories = response.body()?.map {
                    RepositoryListItem(it.name)
                }
                if (repositories == null) {
                    emitter.onNext(arrayListOf())
                } else {
                    emitter.onNext(repositories)
                }
                emitter.onComplete()
            } else {
                emitter.onError(Throwable(response.message()))
            }
        }
        return observable.observeOn(schedulerProvider.mainThread).subscribeOn(schedulerProvider.ioThread)
    }

    private fun createMockRepositoryList() = (1..10).map { RepositoryListItem("Github Repository: " + it) }
}
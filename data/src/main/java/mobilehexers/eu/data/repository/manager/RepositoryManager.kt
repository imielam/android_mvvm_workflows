/*
 * Copyright (c) 2017.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.data.repository.manager

import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import mobilehexers.eu.data.base.retrofit.RestAPI
import mobilehexers.eu.data.repository.GithubRepositoryDetailsResponse
import mobilehexers.eu.domain.base.di.ActivitySingleton
import mobilehexers.eu.domain.base.rx.SchedulerProvider
import mobilehexers.eu.domain.repository.details.entity.RepositoryDetailsItem
import mobilehexers.eu.domain.repository.list.entity.RepositoryListItem
import javax.inject.Inject

//FIXME: Add Abstraction layer
@ActivitySingleton
class RepositoryManager @Inject constructor(private val schedulerProvider: SchedulerProvider, private val api: RestAPI) {

    private val defaultUserName = "square"
    private val defaultRepositoryName = "retrofit"
    fun getRepositoryList(username: String = defaultUserName): Observable<List<RepositoryListItem>> {

        val observable = Observable.create<List<RepositoryListItem>> { emitter: ObservableEmitter<List<RepositoryListItem>> ->
            val callResponse = api.getRepositoryList(username)
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

    fun getRepositoryDetail(username: String = defaultUserName, repositoryName: String = defaultRepositoryName): Observable<RepositoryDetailsItem> {
        val observable = Observable.create<RepositoryDetailsItem> { emitter: ObservableEmitter<RepositoryDetailsItem> ->
            val callResponse = api.getRepositoryDetails(username, repositoryName)
            val response = callResponse.execute()
            if (response.isSuccessful) {
                val responseItem: GithubRepositoryDetailsResponse = if (response.body() == null) {
                    GithubRepositoryDetailsResponse("", "", "", "")
                } else {
                    response.body()!!
                }
                val details = RepositoryDetailsItem(responseItem.id, responseItem.name, responseItem.description, responseItem.language)
                emitter.onNext(details)
                emitter.onComplete()
            } else {
                emitter.onError(Throwable(response.message()))
            }
        }
        return observable.observeOn(schedulerProvider.mainThread).subscribeOn(schedulerProvider.ioThread)
    }
}
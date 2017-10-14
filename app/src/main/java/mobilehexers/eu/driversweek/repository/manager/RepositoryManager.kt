/*
 * Copyright (c) 2017.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.driversweek.repository.manager

import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import mobilehexers.eu.data.base.retrofit.RestAPI
import mobilehexers.eu.data.repository.GithubRepositoryDetailsResponse
import mobilehexers.eu.domain.rx.SchedulerProvider
import mobilehexers.eu.driversweek.repository.details.RepositoryDetailsItem
import mobilehexers.eu.driversweek.repository.list.RepositoryListItem
import javax.inject.Inject

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

    private fun createMockRepositoryList() = (1..10).map {
        RepositoryListItem("Github Repository: " + it)

    }

    private fun createMockedRepositoryDetailsItem() = RepositoryDetailsItem("01", "retrofit", "Type-safe HTTP client for Android and Java by Square, Inc.", "Java")
}
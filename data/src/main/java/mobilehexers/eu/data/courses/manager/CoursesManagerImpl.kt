/*
 * Copyright (c) 2017.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.data.courses.manager

import io.reactivex.Single
import io.reactivex.SingleEmitter
import mobilehexers.eu.data.courses.api.CoursesRestAPI
import mobilehexers.eu.domain.base.rx.SchedulerProvider
import mobilehexers.eu.domain.courses.list.entity.CoursesListEntity
import mobilehexers.eu.domain.courses.manager.CoursesManager
import javax.inject.Inject

class CoursesManagerImpl @Inject constructor(private val schedulerProvider: SchedulerProvider, private val api: CoursesRestAPI) : CoursesManager {
    override fun getCourses(): Single<List<CoursesListEntity>> {
        val observable = Single.create<List<CoursesListEntity>> { emitter: SingleEmitter<List<CoursesListEntity>> ->
            val callResponse = api.getCoursesList()
            val response = callResponse.execute()
            if (response.isSuccessful) {
                val courses = response.body()?.map { CoursesListEntity(it.name, it.description) } ?: arrayListOf()
                emitter.onSuccess(courses)
            } else {
                emitter.onError(Throwable(response.message()))
            }
        }
        return observable.observeOn(schedulerProvider.mainThread).subscribeOn(schedulerProvider.ioThread)
    }
}
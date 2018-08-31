/*
 * Copyright (c) 2018.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.domain.weather.repository

import android.arch.paging.PagedList
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import mobilehexers.eu.domain.weather.list.entity.City

interface CityRepository {
    val cities: Flowable<PagedList<City>>
    val initiated: Single<Boolean>
    fun initDataBase(): Completable
    fun getCitiesWithFilter(text: String): Flowable<PagedList<City>>
}
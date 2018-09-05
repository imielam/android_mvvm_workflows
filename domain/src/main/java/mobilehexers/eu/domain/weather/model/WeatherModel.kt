/*
 * Copyright (c) 2018.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.domain.weather.model

import android.arch.paging.PagedList
import io.reactivex.Flowable
import io.reactivex.Single
import mobilehexers.eu.domain.weather.details.entity.WeatherDetails
import mobilehexers.eu.domain.weather.list.entity.City

interface WeatherModel {
    val cities: Flowable<PagedList<City>>
    var clickedCity: City?

    fun getDetailsFor(cityId: Long): Single<WeatherDetails>
    fun getCitiesWithFilter(text: String): Flowable<PagedList<City>>
}
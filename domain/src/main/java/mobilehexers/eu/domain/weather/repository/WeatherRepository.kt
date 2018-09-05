/*
 * Copyright (c) 2018.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.domain.weather.repository

import io.reactivex.Single
import mobilehexers.eu.domain.weather.details.entity.WeatherDetails

interface WeatherRepository {
    fun getWeatherDataFor(id: Long): Single<WeatherDetails>
    fun getOfflineDataFor(cityId: Long): Single<WeatherDetails>
    fun saveOfflineData(data: WeatherDetails)
}
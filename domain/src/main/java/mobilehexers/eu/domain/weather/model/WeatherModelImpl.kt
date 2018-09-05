/*
 * Copyright (c) 2018.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.domain.weather.model

import android.arch.paging.PagedList
import io.reactivex.Flowable
import io.reactivex.Single
import mobilehexers.eu.domain.base.rx.SchedulerProvider
import mobilehexers.eu.domain.extensions.logTag
import mobilehexers.eu.domain.weather.details.entity.WeatherDetails
import mobilehexers.eu.domain.weather.list.entity.City
import mobilehexers.eu.domain.weather.repository.CityRepository
import mobilehexers.eu.domain.weather.repository.WeatherRepository
import javax.inject.Inject

class WeatherModelImpl(
        private val cityRepository: CityRepository,
        private val weatherRepository: WeatherRepository
) : WeatherModel {
    override fun getCitiesWithFilter(text: String) = cityRepository.getCitiesWithFilter(text)

    override var clickedCity: City? = null
    override val cities: Flowable<PagedList<City>> =
            cityRepository.cities.cache()

    override fun getDetailsFor(cityId: Long): Single<WeatherDetails> =
            weatherRepository.getWeatherDataFor(cityId)
                    .map {
                        weatherRepository.saveOfflineData(it)
                        it
                    }
                    .onErrorResumeNext {
                        weatherRepository.getOfflineDataFor(cityId).map { it.copy(offline = true) }
                    }
}

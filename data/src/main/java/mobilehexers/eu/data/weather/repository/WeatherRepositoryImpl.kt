/*
 * Copyright (c) 2018.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.data.weather.repository

import android.util.Log
import io.reactivex.Single
import mobilehexers.eu.data.weather.api.WeatherRestAPI
import mobilehexers.eu.data.weather.data.toData
import mobilehexers.eu.data.weather.data.toEntity
import mobilehexers.eu.data.weather.database.WeatherDatabase
import mobilehexers.eu.data.weather.response.WeatherApiData
import mobilehexers.eu.domain.extensions.logTag
import mobilehexers.eu.domain.weather.details.entity.WeatherDetails
import mobilehexers.eu.domain.weather.repository.WeatherRepository

class WeatherRepositoryImpl(
        private val api: WeatherRestAPI,
        private val database: WeatherDatabase
) : WeatherRepository {

    override fun getOfflineDataFor(cityId: Long): Single<WeatherDetails> =
            database.weatherDao()
                    .getDetailsFor(cityId)
                    .flatMap {
                        if (it.isEmpty()) {
                            Single.error<WeatherDetails>(NoSuchElementException("Item not found in database!"))
                        } else {
                            Single.just(it.first().toEntity())
                        }
                    }

    override fun saveOfflineData(data: WeatherDetails) {
        Log.w(logTag, data.toString())
        database.weatherDao().insert(data.toData())
    }

    override fun getWeatherDataFor(id: Long): Single<WeatherDetails> =
            api.getWeatherDataFor(id)
                    .map { it.toEntity() }
}

private fun WeatherApiData.toEntity(): WeatherDetails =
        WeatherDetails(
                id = id.toLong(),
                cityName = name,
                cloudiness = weather.first().description,
                humidity = main.humidity,
                pressure = main.pressure,
                temperature = main.temp,
                windSpeed = wind.speed
        )

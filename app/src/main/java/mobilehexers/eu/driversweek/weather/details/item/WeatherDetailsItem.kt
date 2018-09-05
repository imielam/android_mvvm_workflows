/*
 * Copyright (c) 2018.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.driversweek.weather.details.item

import mobilehexers.eu.domain.weather.details.entity.WeatherDetails

data class WeatherDetailsItem(
        val cityName: String,
        val temperature: String,
        val windSpeed: String,
        val cloudiness: String,
        val pressure: String,
        val humidity: String
)

internal fun WeatherDetails.toItem() = WeatherDetailsItem(
        cityName = cityName,
        temperature = temperature.toString(),
        cloudiness = cloudiness,
        windSpeed = windSpeed.toString(),
        pressure = pressure.toString(),
        humidity = humidity.toString()
)
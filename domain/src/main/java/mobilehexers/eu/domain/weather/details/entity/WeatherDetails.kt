/*
 * Copyright (c) 2018.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.domain.weather.details.entity

data class WeatherDetails(
        val id: Long,
        val cityName: String,
        val temperature: Double,
        val windSpeed: Double,
        val cloudiness: String,
        val pressure: Double,
        val humidity: Double,
        val offline: Boolean = false
)
/*
 * Copyright (c) 2018.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.data.weather.data

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import mobilehexers.eu.domain.weather.details.entity.WeatherDetails

@Entity(tableName = "weather_details")
data class WeatherDetailsData(
        @PrimaryKey @ColumnInfo(name = "id") val id: Long,
        @ColumnInfo(name = "city_name") val cityName: String,
        @ColumnInfo(name = "temperature") val temperature: Double,
        @ColumnInfo(name = "wind_speed") val windSpeed: Double,
        @ColumnInfo(name = "cloudiness") val cloudiness: String,
        @ColumnInfo(name = "pressure") val pressure: Double,
        @ColumnInfo(name = "humidity") val humidity: Double
)


internal fun WeatherDetails.toData(): WeatherDetailsData {
    return WeatherDetailsData(id, cityName, temperature, windSpeed, cloudiness, pressure, humidity)
}

internal fun WeatherDetailsData.toEntity() = WeatherDetails(id, cityName, temperature, windSpeed, cloudiness, pressure, humidity)
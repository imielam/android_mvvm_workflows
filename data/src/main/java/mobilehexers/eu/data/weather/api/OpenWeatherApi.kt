/*
 * Copyright (c) 2018.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.data.weather.api

import io.reactivex.Single
import mobilehexers.eu.data.weather.response.WeatherApiData
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherApi {

    @GET("weather")
    fun getWeatherDataFor(
            @Query("id") id: Long,
            @Query("APPID") apiId: String = APPID
    ): Single<WeatherApiData>

    private companion object {
        const val APPID = "ea61e62146f59affca597e47557226c9"
    }
}

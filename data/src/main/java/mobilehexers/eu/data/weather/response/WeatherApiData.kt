/*
 * Copyright (c) 2018.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.data.weather.response

import com.squareup.moshi.Json

data class WeatherApiData(@Json(name = "dt")
                          val dt: Int = 0,
                          @Json(name = "coord")
                          val coord: Coord,
                          @Json(name = "visibility")
                          val visibility: Int = 0,
                          @Json(name = "weather")
                          val weather: List<WeatherItem>,
                          @Json(name = "name")
                          val name: String = "",
                          @Json(name = "cod")
                          val cod: Int = 0,
                          @Json(name = "main")
                          val main: Main,
                          @Json(name = "clouds")
                          val clouds: Clouds,
                          @Json(name = "id")
                          val id: Int = 0,
                          @Json(name = "sys")
                          val sys: Sys,
                          @Json(name = "base")
                          val base: String = "",
                          @Json(name = "wind")
                          val wind: Wind)
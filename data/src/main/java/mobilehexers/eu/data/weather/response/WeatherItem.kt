/*
 * Copyright (c) 2018.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.data.weather.response

import com.squareup.moshi.Json

data class WeatherItem(@Json(name = "icon")
                       val icon: String = "",
                       @Json(name = "description")
                       val description: String = "",
                       @Json(name = "main")
                       val main: String = "",
                       @Json(name = "id")
                       val id: Int = 0)
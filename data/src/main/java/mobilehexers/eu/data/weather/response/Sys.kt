/*
 * Copyright (c) 2018.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.data.weather.response

import com.squareup.moshi.Json

data class Sys(@Json(name = "country")
               val country: String = "",
               @Json(name = "sunrise")
               val sunrise: Long = 0,
               @Json(name = "sunset")
               val sunset: Long = 0,
               @Json(name = "id")
               val id: Int = 0,
               @Json(name = "type")
               val type: Int = 0,
               @Json(name = "message")
               val message: String = "")
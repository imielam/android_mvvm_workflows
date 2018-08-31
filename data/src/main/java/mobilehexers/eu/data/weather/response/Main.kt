/*
 * Copyright (c) 2018.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.data.weather.response

import com.squareup.moshi.Json

data class Main(@Json(name = "temp")
                val temp: Double = 0.0,
                @Json(name = "temp_min")
                val tempMin: Double = 0.0,
                @Json(name = "humidity")
                val humidity: Double = 0.0,
                @Json(name = "pressure")
                val pressure: Double = 0.0,
                @Json(name = "temp_max")
                val tempMax: Double = 0.0)
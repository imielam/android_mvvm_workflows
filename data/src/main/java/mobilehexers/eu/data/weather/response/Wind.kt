/*
 * Copyright (c) 2018.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.data.weather.response

import com.squareup.moshi.Json

data class Wind(@Json(name = "deg")
                val deg: Double = 0.0,
                @Json(name = "speed")
                val speed: Double = 0.0)
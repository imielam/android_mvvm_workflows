/*
 * Copyright (c) 2018.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.data.weather.response

import com.squareup.moshi.Json

data class Clouds(@Json(name = "all")
                  val all: Int = 0)
/*
 * Copyright (c) 2018.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.driversweek.weather.list.item

import mobilehexers.eu.domain.weather.list.entity.City

interface OnItemClickListener {
    fun onClick(cityItem: City)
}
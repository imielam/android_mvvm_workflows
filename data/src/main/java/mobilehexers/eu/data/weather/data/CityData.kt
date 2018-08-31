/*
 * Copyright (c) 2018.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.data.weather.data

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.squareup.moshi.Json
import mobilehexers.eu.domain.weather.list.entity.City

@Entity(tableName = "city")
data class CityData(
        @ColumnInfo(name = "name") @Json(name = "name") var name: String = "",
        @PrimaryKey @Json(name = "id") var id: Long = 0
)

internal fun CityData.toEntity() = City(id, name)
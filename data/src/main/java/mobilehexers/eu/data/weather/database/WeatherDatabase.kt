/*
 * Copyright (c) 2018.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.data.weather.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import mobilehexers.eu.data.weather.data.CityData
import mobilehexers.eu.data.weather.data.WeatherDetailsData

@Database(entities = [CityData::class, WeatherDetailsData::class], version = 1)
abstract class WeatherDatabase: RoomDatabase() {
    abstract fun cityDao(): CityDao
    abstract fun weatherDao(): WeatherDao
}
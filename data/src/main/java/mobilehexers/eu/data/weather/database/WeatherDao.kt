/*
 * Copyright (c) 2018.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.data.weather.database

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import io.reactivex.Single
import mobilehexers.eu.data.weather.data.WeatherDetailsData

@Dao
interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(data: WeatherDetailsData)

    @Query("SELECT * FROM weather_details WHERE id=:id")
    fun getDetailsFor(id: Long): Single<List<WeatherDetailsData>>
}

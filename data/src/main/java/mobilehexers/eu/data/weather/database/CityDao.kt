/*
 * Copyright (c) 2018.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.data.weather.database

import android.arch.paging.DataSource
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import io.reactivex.Single
import mobilehexers.eu.data.weather.data.CityData

@Dao
interface CityDao {
    @Insert fun insertAll(cities: List<CityData>)

    @Query("SELECT * FROM city ORDER BY name ASC")
    fun getAll(): DataSource.Factory<Int, CityData?>

    @Query("SELECT * FROM city WHERE name LIKE :text ORDER BY name ASC")
    fun getFiltered(text: String): DataSource.Factory<Int, CityData?>

    @Query("SELECT * FROM city LIMIT 1")
    fun getOneCity(): Single<List<CityData>>

}
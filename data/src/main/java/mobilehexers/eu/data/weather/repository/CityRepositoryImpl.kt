/*
 * Copyright (c) 2018.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.data.weather.repository

import android.arch.paging.PagedList
import android.arch.paging.RxPagedListBuilder
import android.content.Context
import android.util.Log
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import io.reactivex.BackpressureStrategy
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import mobilehexers.eu.data.weather.data.CityData
import mobilehexers.eu.data.weather.data.toEntity
import mobilehexers.eu.data.weather.database.WeatherDatabase
import mobilehexers.eu.domain.extensions.logTag
import mobilehexers.eu.domain.weather.list.entity.City
import mobilehexers.eu.domain.weather.repository.CityRepository
import javax.inject.Inject

class CityRepositoryImpl
@Inject constructor(
        private val context: Context,
        database: WeatherDatabase
) : CityRepository {

    private val cityDao = database.cityDao()

    override fun getCitiesWithFilter(text: String): Flowable<PagedList<City>> =
            RxPagedListBuilder(
                    cityDao.getFiltered("%$text%")
                            .map {
                                it?.toEntity() ?: City(-1, "")
                            }, 200
            )
                    .buildFlowable(BackpressureStrategy.BUFFER)

    override fun initDataBase(): Completable =
            Completable.fromAction {
                val data = stringFromJsonFile.toCityData()
                Log.e(logTag, data.toString())
                cityDao.insertAll(data)
            }

    override val cities: Flowable<PagedList<City>> =
            RxPagedListBuilder(
                    cityDao.getAll()
                            .map {
                                it?.toEntity() ?: City(-1, "")
                            }
                    , 200
            ).buildFlowable(BackpressureStrategy.BUFFER)

    override val initiated: Single<Boolean>
        get() = cityDao.getOneCity()
                .map { it.isNotEmpty() }

    private val stringFromJsonFile: String
        get() {
            return context.assets.open(JSON_FILE_NAME).bufferedReader().use {
                it.readText()
            }
        }

    private companion object {
        const val JSON_FILE_NAME = "city.min.list.json"
    }

}

private fun String.toCityData(): List<CityData> {
    val type = Types.newParameterizedType(List::class.java, CityData::class.java)
    val jsonAdapter = Moshi.Builder().build().adapter<List<CityData>>(type)

    return jsonAdapter.fromJson(this)
}
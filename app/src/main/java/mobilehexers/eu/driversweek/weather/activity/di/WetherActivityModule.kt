/*
 * Copyright (c) 2018.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.driversweek.weather.activity.di

import android.content.Context
import dagger.Module
import dagger.Provides
import mobilehexers.eu.data.weather.api.WeatherRestAPI
import mobilehexers.eu.data.weather.database.WeatherDatabase
import mobilehexers.eu.data.weather.repository.CityRepositoryImpl
import mobilehexers.eu.data.weather.repository.WeatherRepositoryImpl
import mobilehexers.eu.domain.base.di.ActivitySingleton
import mobilehexers.eu.domain.weather.model.WeatherModel
import mobilehexers.eu.domain.weather.model.WeatherModelImpl
import mobilehexers.eu.domain.weather.repository.CityRepository
import mobilehexers.eu.domain.weather.repository.WeatherRepository

@Module
class WetherActivityModule {

    @Provides
    @ActivitySingleton
    internal fun provideModel(
            cityRepository: CityRepository,
            weatherRepository: WeatherRepository
    ): WeatherModel =
            WeatherModelImpl(cityRepository, weatherRepository)

    @Provides
    @ActivitySingleton
    internal fun getCityRepository(
            context: Context,
            database: WeatherDatabase
    ): CityRepository =
            CityRepositoryImpl(context, database)

    @Provides
    @ActivitySingleton
    internal fun getWeatherRepository(
            api: WeatherRestAPI,
            database: WeatherDatabase
    ): WeatherRepository =
            WeatherRepositoryImpl(api, database)
}

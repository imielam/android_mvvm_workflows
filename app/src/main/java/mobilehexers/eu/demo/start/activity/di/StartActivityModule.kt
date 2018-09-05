/*
 * Copyright (c) 2018.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.demo.start.activity.di

import android.content.Context
import dagger.Module
import dagger.Provides
import mobilehexers.eu.data.weather.database.WeatherDatabase
import mobilehexers.eu.data.weather.repository.CityRepositoryImpl
import mobilehexers.eu.domain.base.di.ActivitySingleton
import mobilehexers.eu.domain.base.rx.SchedulerProvider
import mobilehexers.eu.domain.start.StartModel
import mobilehexers.eu.domain.start.StartModelImpl
import mobilehexers.eu.domain.weather.repository.CityRepository

@Module
class StartActivityModule {

    @Provides
    @ActivitySingleton
    internal fun provideModel(
            cityRepository: CityRepository,
            schedulerProvider: SchedulerProvider
    ): StartModel = StartModelImpl(
            cityRepository,
            schedulerProvider
    )

    @Provides
    @ActivitySingleton
    internal fun getCityRepository(
            context: Context,
            database: WeatherDatabase
    ): CityRepository = CityRepositoryImpl(
            context,
            database
    )
}
/*
 * Copyright (c) 2017.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.demo.base.di.application

import android.app.Application
import android.arch.persistence.room.Room
import android.content.Context
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper
import dagger.Module
import dagger.Provides
import mobilehexers.eu.data.repository.api.RepositoryRestAPI
import mobilehexers.eu.data.weather.api.WeatherRestAPI
import mobilehexers.eu.data.weather.database.WeatherDatabase
import mobilehexers.eu.domain.base.rx.SchedulerProvider
import mobilehexers.eu.presentation.main.workflow.MainWorkflow
import mobilehexers.eu.presentation.repository.workflow.RepositoryWorkflow
import mobilehexers.eu.presentation.start.workflow.StartWorkflow
import mobilehexers.eu.presentation.wether.workflow.WeatherWorkflow
import mobilehexers.eu.uibase.base.rx.AndroidSchedulerProvider
import javax.inject.Singleton

/**
 * Created by maciej.imiela on 25.12.16.
 */

@Module
class ApplicationModule {

    @Provides
    @Singleton
    internal fun provideContext(application: Application): Context = application

    @Provides
    @Singleton
    internal fun provideSchedulerProvider(): SchedulerProvider = AndroidSchedulerProvider()

    @Provides
    @Singleton
    internal fun provideStartWorkflow(schedulerProvider: SchedulerProvider) = StartWorkflow(schedulerProvider)

    @Provides
    @Singleton
    internal fun provideMainWorkflow(schedulerProvider: SchedulerProvider) = MainWorkflow(schedulerProvider)

    @Provides
    @Singleton
    internal fun provideRepositoryWorkflow(schedulerProvider: SchedulerProvider) = RepositoryWorkflow(schedulerProvider)

    @Provides
    @Singleton
    internal fun providesWeatherWorkflow(schedulerProvider: SchedulerProvider) = WeatherWorkflow(schedulerProvider)

    @Provides
    @Singleton
    internal fun provideRepositoryRestAPI() = RepositoryRestAPI()

    @Provides
    @Singleton
    internal fun provideWeatherRestAPI() = WeatherRestAPI()

    @Provides
    @Singleton
    internal fun provideDB(context: Context): WeatherDatabase {
        SQLiteAssetHelper(context, DB_NAME, null, null, 1).writableDatabase.close()

        return Room.databaseBuilder(context, WeatherDatabase::class.java, DB_NAME)
                .fallbackToDestructiveMigration().build()
    }

    private companion object {
        const val DB_NAME = "city-database.db"
    }

}

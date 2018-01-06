/*
 * Copyright (c) 2017.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.driversweek.courses.activity.di

import dagger.Module
import dagger.Provides
import mobilehexers.eu.data.courses.api.CoursesRestAPI
import mobilehexers.eu.data.courses.manager.CoursesManagerImpl
import mobilehexers.eu.domain.base.di.ActivitySingleton
import mobilehexers.eu.domain.base.rx.SchedulerProvider
import mobilehexers.eu.domain.courses.manager.CoursesManager
import mobilehexers.eu.domain.courses.model.CoursesModel

@Module
class CoursesActivityModule {

    @Provides
    @ActivitySingleton
    internal fun provideModel(manager: CoursesManager) = CoursesModel(manager)

    @Provides
    @ActivitySingleton internal fun getRepositoryManager(schedulerProvider: SchedulerProvider,
                                                         restAPI: CoursesRestAPI): CoursesManager = CoursesManagerImpl(schedulerProvider, restAPI)
}

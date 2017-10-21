/*
 * Copyright (c) 2017.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.driversweek.repository.dependencyinjection

import dagger.Module
import dagger.Provides
import mobilehexers.eu.data.repository.api.RepositoryRestAPI
import mobilehexers.eu.data.repository.manager.RepositoryManager
import mobilehexers.eu.domain.base.di.ActivitySingleton
import mobilehexers.eu.domain.base.rx.SchedulerProvider
import mobilehexers.eu.driversweek.repository.model.RepositoryModel
import mobilehexers.eu.driversweek.repository.model.RepositoryModelImpl

@Module
class RepositoryActivityModule {

    @Provides
    @ActivitySingleton
    internal fun provideRepositoryModel(repositoryManager: RepositoryManager): RepositoryModel = RepositoryModelImpl(repositoryManager)

    @Provides
    @ActivitySingleton
    internal fun getRepositoryManager(schedulerProvider: SchedulerProvider, restAPI: RepositoryRestAPI): RepositoryManager = RepositoryManager(schedulerProvider, restAPI)
}

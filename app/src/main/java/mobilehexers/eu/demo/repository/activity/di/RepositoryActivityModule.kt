/*
 * Copyright (c) 2017.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.demo.repository.activity.di

import dagger.Module
import dagger.Provides
import mobilehexers.eu.data.repository.api.RepositoryRestAPI
import mobilehexers.eu.data.repository.manager.RepositoryManagerImpl
import mobilehexers.eu.domain.base.di.ActivitySingleton
import mobilehexers.eu.domain.base.rx.SchedulerProvider
import mobilehexers.eu.domain.repository.manager.RepositoryManager
import mobilehexers.eu.domain.repository.model.RepositoryModel
import mobilehexers.eu.domain.repository.model.RepositoryModelImpl

@Module
class RepositoryActivityModule {

    @Provides
    @ActivitySingleton internal fun provideRepositoryModel(repositoryManager: RepositoryManager): RepositoryModel = RepositoryModelImpl(
            repositoryManager)

    @Provides
    @ActivitySingleton internal fun getRepositoryManager(schedulerProvider: SchedulerProvider,
            restAPI: RepositoryRestAPI): RepositoryManager = RepositoryManagerImpl(schedulerProvider, restAPI)
}

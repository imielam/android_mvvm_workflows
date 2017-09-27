package com.mobilehexers.driversweek.base.dependencyinjection.module

import dagger.Module
import dagger.Provides
import mobilehexers.eu.domain.workflow.base.Workflow
import mobilehexers.eu.domain.workflow.main.MainState
import mobilehexers.eu.domain.workflow.start.StartState
import javax.inject.Named
import javax.inject.Singleton

/**
 * Created by maciej.imiela on 25.12.16.
 */

@Module class ApplicationModule {

}
//
//    @Provides
//    @Singleton
//    internal fun provideNetworkManager(): NetworkManager {
//        return NetworkManagerImpl()
//    }
//
//    @Provides
//    @Singleton
//    internal fun provideResponseHandler(): ResponseHandler {
//        return Fetcher(application, DataFetchMapper())
//    }
//
//    @Provides
//    @Singleton
//    internal fun provideStackManager(): StackManager {
//        return StackManager()
//    }

package com.mobilehexers.driversweek.base.dependencyinjection.module

import android.app.Application
import android.content.Context
import com.mobilehexers.driversweek.base.dependencyinjection.component.MainComponent
import dagger.Module
import dagger.Provides
import mobilehexers.eu.domain.rx.SchedulerProvider
import mobilehexers.eu.domain.workflow.main.MainWorkflow
import mobilehexers.eu.domain.workflow.start.StartWorkflow
import mobilehexers.eu.driversweek.base.rx.AndroidSchedulerProvider
import javax.inject.Singleton

/**
 * Created by maciej.imiela on 25.12.16.
 */

@Module/*(subcomponents = arrayOf(MainComponent::class))*/
class ApplicationModule constructor(val application: Application){

    @Provides
    @Singleton
    internal fun provideContext(): Context = application

    @Provides
    @Singleton
    internal fun provideSchedulerProvider(): SchedulerProvider = AndroidSchedulerProvider()

    @Provides
    @Singleton
    internal fun provideStartWorkflow() = StartWorkflow(AndroidSchedulerProvider())

    @Provides
    @Singleton
    internal fun provideMainWorkflow() = MainWorkflow(AndroidSchedulerProvider())

//    @Provides
//    @Singleton
//    internal fun provideStartWorkflow() = StartWorkflow(AndroidSchedulerProvider())
//
//    @Provides
//    @Singleton
//    internal fun provideMainWorkflow() = MainWorkflow(AndroidSchedulerProvider())
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

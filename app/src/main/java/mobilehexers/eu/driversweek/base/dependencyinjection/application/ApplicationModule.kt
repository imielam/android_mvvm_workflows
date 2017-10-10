/*
 * Copyright (c) 2017.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.driversweek.base.dependencyinjection.application

import android.app.Application
import android.content.Context
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
    internal fun provideStartWorkflow() = StartWorkflow(AndroidSchedulerProvider())

    @Provides
    @Singleton
    internal fun provideMainWorkflow() = MainWorkflow(AndroidSchedulerProvider())

}
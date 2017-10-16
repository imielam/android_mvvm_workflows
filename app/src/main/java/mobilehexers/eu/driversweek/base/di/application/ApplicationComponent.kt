/*
 * Copyright (c) 2017.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.driversweek.base.di.application

import android.app.Application
import com.mobilehexers.driversweek.base.dependencyinjection.module.MainActivityModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import mobilehexers.eu.domain.base.rx.SchedulerProvider
import mobilehexers.eu.driversweek.base.android.BaseApplication
import mobilehexers.eu.driversweek.repository.dependencyinjection.RepositoryActivityModule
import mobilehexers.eu.driversweek.start.di.StartActivityModule
import mobilehexers.eu.presentation.main.workflow.MainWorkflow
import mobilehexers.eu.presentation.repository.workflow.RepositoryWorkflow
import mobilehexers.eu.presentation.start.workflow.StartWorkflow
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AndroidInjectionModule::class, ApplicationModule::class, StartActivityModule::class, MainActivityModule::class,
        RepositoryActivityModule::class))
interface ApplicationComponent : AndroidInjector<BaseApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }

    val mainWorkflow: MainWorkflow
    val startWorkflow: StartWorkflow
    val repositoryWorkflow: RepositoryWorkflow
    val schedulerProvider: SchedulerProvider
}
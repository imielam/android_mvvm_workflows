/*
 * Copyright (c) 2017.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.driversweek.base.di.application

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import mobilehexers.eu.domain.workflow.main.MainWorkflow
import mobilehexers.eu.domain.workflow.start.StartWorkflow
import mobilehexers.eu.driversweek.base.android.BaseApplication
import mobilehexers.eu.presentation.repository.workflow.RepositoryWorkflow
import javax.inject.Singleton

/**
 * Created by mimiela on 10.10.17.
 */
@Singleton
@Component(modules = arrayOf(AndroidInjectionModule::class, ActivityProvider::class, ApplicationModule::class))
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
}
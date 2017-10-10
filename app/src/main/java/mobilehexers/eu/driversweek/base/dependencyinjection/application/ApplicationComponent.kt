package com.mobilehexers.driversweek.base.dependencyinjection.component

import android.content.Context
import com.mobilehexers.driversweek.base.dependencyinjection.module.ApplicationModule
import com.mobilehexers.driversweek.base.dependencyinjection.module.MainActivityModule
import dagger.Component
import mobilehexers.eu.domain.workflow.main.MainWorkflow
import mobilehexers.eu.domain.workflow.start.StartWorkflow
import mobilehexers.eu.driversweek.main.MainActivity
import mobilehexers.eu.driversweek.start.StartActivity
import javax.inject.Singleton

/**
 * Created by maciej.imiela on 25.12.16.
 */
@Singleton
@Component(modules = arrayOf(ApplicationModule::class))
interface ApplicationComponent {
    fun plus(module: MainActivityModule): MainActivityComponent

    fun inject(activity: MainActivity)
    fun inject(activity: StartActivity)

    val context: Context
    val mainWorkflow: MainWorkflow
    val startWorkflow: StartWorkflow
}
package com.mobilehexers.driversweek.base.dependencyinjection.component

import android.content.Context
import com.mobilehexers.driversweek.base.dependencyinjection.module.ApplicationModule
import com.mobilehexers.driversweek.base.dependencyinjection.module.MainModule
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
    fun plus(module: MainModule) : MainComponent

    fun inject(activity: MainActivity)
    fun inject(activity: StartActivity)

    //    fun inject(app: Application)
//    fun plus(startModule: StartModule): StartComponent
//
//    fun plus(mainModule: MainModule): MainComponent

//    fun inject(mainActivity: MainActivity)
//    fun inject(startActivity: StartActivity)


    //
//    // void inject(MyFragment fragment);
//    // void inject(MyService service);
//
    val context: Context
    val mainWorkflow: MainWorkflow
    val startWorkflow: StartWorkflow
//    val networkManager: NetworkManager
//    val responseHandler: ResponseHandler
//    val stackManager: StackManager
}
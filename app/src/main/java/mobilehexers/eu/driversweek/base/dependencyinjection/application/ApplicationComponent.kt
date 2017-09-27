package com.mobilehexers.driversweek.base.dependencyinjection.component

import android.support.v7.app.AppCompatActivity
import com.mobilehexers.driversweek.base.dependencyinjection.module.ApplicationModule
import dagger.Component
import javax.inject.Singleton

/**
 * Created by maciej.imiela on 25.12.16.
 */
@Singleton
@Component(modules = arrayOf(ApplicationModule::class))
interface ApplicationComponent {

    fun inject(activity: AppCompatActivity)

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
//    val context: Context
//    val mainWorkflow: Workflow
//    val startWorkflow: Workflow
//    val networkManager: NetworkManager
//    val responseHandler: ResponseHandler
//    val stackManager: StackManager
}
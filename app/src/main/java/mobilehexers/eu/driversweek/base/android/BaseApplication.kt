/*
 * Copyright (c) 2017.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.driversweek.base.android

import android.app.Application
import com.mobilehexers.driversweek.base.dependencyinjection.component.ApplicationComponent
import com.mobilehexers.driversweek.base.dependencyinjection.component.DaggerApplicationComponent
import com.mobilehexers.driversweek.base.dependencyinjection.component.MainComponent
import com.mobilehexers.driversweek.base.dependencyinjection.module.ApplicationModule
import com.mobilehexers.driversweek.base.dependencyinjection.module.MainModule

/**
 * Created by mimiela on 08.06.16.
 */
class BaseApplication : Application() {
    val applicationComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent.builder().applicationModule(ApplicationModule(this)).build()
    }
    var mainComponent: MainComponent? = null
    fun plusMainComponent(): MainComponent? {
        if (mainComponent == null) {
            mainComponent = applicationComponent.plus(MainModule())
        }
        return mainComponent
    }
    fun clearMainComponent() {
        mainComponent = null
    }
}

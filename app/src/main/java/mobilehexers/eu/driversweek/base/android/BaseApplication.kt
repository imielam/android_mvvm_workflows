/*
 * Copyright (c) 2017 MobileHexers.com ALL RIGHTS RESERVED
 */

package mobilehexers.eu.driversweek.base.android

import android.app.Application
import com.mobilehexers.driversweek.base.dependencyinjection.component.ApplicationComponent
import com.mobilehexers.driversweek.base.dependencyinjection.component.DaggerApplicationComponent
import com.mobilehexers.driversweek.base.dependencyinjection.module.ApplicationModule

/**
 * Created by mimiela on 08.06.16.
 */
class BaseApplication : Application() {
    val component: ApplicationComponent by lazy {
        DaggerApplicationComponent.builder().applicationModule(ApplicationModule(this)).build()
    }
}

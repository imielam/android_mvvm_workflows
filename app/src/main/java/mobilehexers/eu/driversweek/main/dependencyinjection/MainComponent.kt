/*
 * Copyright (c) 2017.  All rights reserved - Maciej Imiela.
 */

package com.mobilehexers.driversweek.base.dependencyinjection.component

import com.mobilehexers.driversweek.base.dependencyinjection.annotation.ActivitySingleton
import com.mobilehexers.driversweek.base.dependencyinjection.module.ApplicationModule
import com.mobilehexers.driversweek.base.dependencyinjection.module.MainModule
import dagger.Component
import dagger.Subcomponent
import mobilehexers.eu.driversweek.main.MainActivity
import mobilehexers.eu.driversweek.main.MainFragment
import mobilehexers.eu.driversweek.start.StartActivity
import javax.inject.Singleton

/**
 * Created by maciej.imiela on 25.12.16.
 */
@ActivitySingleton
@Subcomponent(modules = arrayOf(MainModule::class))
interface MainComponent {
    fun inject(fragment: MainFragment)
}
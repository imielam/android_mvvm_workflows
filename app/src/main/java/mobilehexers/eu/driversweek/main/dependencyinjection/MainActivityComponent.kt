/*
 * Copyright (c) 2017.  All rights reserved - Maciej Imiela.
 */

package com.mobilehexers.driversweek.base.dependencyinjection.component

import com.mobilehexers.driversweek.base.dependencyinjection.annotation.ActivitySingleton
import com.mobilehexers.driversweek.base.dependencyinjection.module.MainActivityModule
import dagger.Subcomponent
import mobilehexers.eu.driversweek.main.MainFragment

/**
 * Created by maciej.imiela on 25.12.16.
 */
@ActivitySingleton
@Subcomponent(modules = arrayOf(MainActivityModule::class))
interface MainActivityComponent {
    fun inject(fragment: MainFragment)
}
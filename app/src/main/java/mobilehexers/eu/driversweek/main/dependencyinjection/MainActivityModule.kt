/*
 * Copyright (c) 2017.  All rights reserved - Maciej Imiela.
 */

package com.mobilehexers.driversweek.base.dependencyinjection.module

import android.app.Activity
import dagger.Binds
import dagger.Module
import dagger.android.ActivityKey
import dagger.android.AndroidInjector
import dagger.multibindings.IntoMap
import mobilehexers.eu.driversweek.main.MainActivity
import mobilehexers.eu.driversweek.main.dependencyinjection.MainActivitySubcomponent

@Module(subcomponents = arrayOf(MainActivitySubcomponent::class)) abstract class MainActivityModule {

    @Binds
    @IntoMap
    @ActivityKey(MainActivity::class) internal abstract fun bindAndroidInjectorFactory(
            builder: MainActivitySubcomponent.Builder): AndroidInjector.Factory<out Activity>
}
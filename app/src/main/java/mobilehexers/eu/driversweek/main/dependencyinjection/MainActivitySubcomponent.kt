/*
 * Copyright (c) 2017.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.driversweek.main.dependencyinjection

import com.mobilehexers.driversweek.base.dependencyinjection.module.MainActivityModule
import dagger.Subcomponent
import dagger.android.AndroidInjector
import mobilehexers.eu.driversweek.main.MainActivity
import mobilehexers.eu.uibase.base.di.annotation.ActivitySingleton

@ActivitySingleton
@Subcomponent(modules = arrayOf(MainFragmentProvider::class))
interface MainActivitySubcomponent : AndroidInjector<MainActivity> {

    @Subcomponent.Builder abstract class Builder : AndroidInjector.Builder<MainActivity>()
}
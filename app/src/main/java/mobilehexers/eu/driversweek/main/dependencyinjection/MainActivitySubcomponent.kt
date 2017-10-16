/*
 * Copyright (c) 2017.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.driversweek.main.dependencyinjection

import dagger.Subcomponent
import dagger.android.AndroidInjector
import mobilehexers.eu.domain.base.di.ActivitySingleton
import mobilehexers.eu.driversweek.main.MainActivity

@ActivitySingleton
@Subcomponent(modules = arrayOf(MainFragmentProvider::class))
interface MainActivitySubcomponent : AndroidInjector<MainActivity> {

    @Subcomponent.Builder abstract class Builder : AndroidInjector.Builder<MainActivity>()
}
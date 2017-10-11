/*
 * Copyright (c) 2017.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.driversweek.main.dependencyinjection

import dagger.Module
import dagger.android.ContributesAndroidInjector
import mobilehexers.eu.driversweek.main.MainFragment

@Module abstract class MainFragmentProvider {
    @ContributesAndroidInjector(modules = arrayOf(MainFragmentModule::class)) abstract fun contributeMainFragmentInjector(): MainFragment
}
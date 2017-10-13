/*
 * Copyright (c) 2017.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.driversweek.main.dependencyinjection

import dagger.Module
import dagger.android.ContributesAndroidInjector
import mobilehexers.eu.driversweek.main.MainFragment
import mobilehexers.eu.uibase.base.di.annotation.FragmentSingleton

@Module abstract class MainFragmentProvider {
    @FragmentSingleton
    @ContributesAndroidInjector(modules = arrayOf(MainFragmentModule::class)) abstract fun contributeMainFragmentInjector(): MainFragment
}
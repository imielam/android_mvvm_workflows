/*
 * Copyright (c) 2017. All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.driversweek.main.dependencyinjection.main

import dagger.Module
import dagger.android.ContributesAndroidInjector
import mobilehexers.eu.domain.base.di.FragmentSingleton
import mobilehexers.eu.driversweek.main.MainFragment

@Module abstract class MainFragmentProvider {
    @FragmentSingleton
    @ContributesAndroidInjector(modules = arrayOf(MainFragmentModule::class)) abstract fun contributeMainFragmentInjector(): MainFragment
}

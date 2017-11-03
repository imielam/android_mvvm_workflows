/*
 * Copyright (c) 2017.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.driversweek.main.activity.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import mobilehexers.eu.domain.base.di.FragmentSingleton
import mobilehexers.eu.driversweek.main.list.MainListFragment
import mobilehexers.eu.driversweek.main.list.di.MainListFragmentModule

@Module abstract class MainFragmentProvider {
    @FragmentSingleton
    @ContributesAndroidInjector(modules = arrayOf(MainListFragmentModule::class)) abstract fun contributeMainFragmentInjector(): MainListFragment
}

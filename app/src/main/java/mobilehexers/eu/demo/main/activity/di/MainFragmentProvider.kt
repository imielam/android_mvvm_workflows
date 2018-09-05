/*
 * Copyright (c) 2017.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.demo.main.activity.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import mobilehexers.eu.domain.base.di.FragmentSingleton
import mobilehexers.eu.demo.main.list.MainListFragment
import mobilehexers.eu.demo.main.list.di.MainListFragmentModule

@Module abstract class MainFragmentProvider {
    @FragmentSingleton
    @ContributesAndroidInjector(modules = arrayOf(MainListFragmentModule::class)) abstract fun contributeMainFragmentInjector(): MainListFragment
}

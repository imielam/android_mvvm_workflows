/*
 * Copyright (c) 2018.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.demo.start.activity.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import mobilehexers.eu.domain.base.di.FragmentSingleton
import mobilehexers.eu.demo.start.loading.LoadingFragment
import mobilehexers.eu.demo.start.loading.di.LoadingFragmentModule

@Module
abstract class StartFragmentsProvider {
    @FragmentSingleton
    @ContributesAndroidInjector(modules = [LoadingFragmentModule::class])
    abstract fun contributeLoadingFragmentInjector(): LoadingFragment
}
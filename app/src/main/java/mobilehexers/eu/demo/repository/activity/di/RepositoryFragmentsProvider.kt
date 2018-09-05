/*
 * Copyright (c) 2017.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.demo.repository.activity.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import mobilehexers.eu.domain.base.di.FragmentSingleton
import mobilehexers.eu.demo.repository.details.RepositoryDetailsFragment
import mobilehexers.eu.demo.repository.details.di.RepositoryDetailsFragmentsModule
import mobilehexers.eu.demo.repository.list.RepositoryListFragment
import mobilehexers.eu.demo.repository.list.di.RepositoryListFragmentsModule

@Module abstract class RepositoryFragmentsProvider {
    @FragmentSingleton
    @ContributesAndroidInjector(modules = arrayOf(RepositoryListFragmentsModule::class)) abstract fun contributeListFragmentInjector(): RepositoryListFragment

    @FragmentSingleton
    @ContributesAndroidInjector(modules = arrayOf(RepositoryDetailsFragmentsModule::class)) abstract fun contributeDetailsragmentInjector(): RepositoryDetailsFragment
}
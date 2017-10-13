/*
 * Copyright (c) 2017.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.driversweek.repository.dependencyinjection

import dagger.Module
import dagger.android.ContributesAndroidInjector
import mobilehexers.eu.driversweek.repository.details.RepositoryDetailsFragment
import mobilehexers.eu.driversweek.repository.list.RepositoryListFragment
import mobilehexers.eu.uibase.base.di.annotation.FragmentSingleton

@Module abstract class RepositoryFragmentsProvider {
    @FragmentSingleton
    @ContributesAndroidInjector(modules = arrayOf(RepositoryFragmentsModule::class)) abstract fun contributeListFragmentInjector(): RepositoryListFragment

    @FragmentSingleton
    @ContributesAndroidInjector(modules = arrayOf(RepositoryFragmentsModule::class)) abstract fun contributeDetailsragmentInjector(): RepositoryDetailsFragment
}
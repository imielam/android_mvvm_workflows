/*
 * Copyright (c) 2017.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.driversweek.repository.dependencyinjection

import dagger.Module
import dagger.android.ContributesAndroidInjector
import mobilehexers.eu.driversweek.repository.details.RepositoryDetailsFragment
import mobilehexers.eu.driversweek.repository.list.RepositoryListFragment

@Module abstract class RepositoryFragmentsProvider {
    @ContributesAndroidInjector(modules = arrayOf(RepositoryFragmentsModule::class)) abstract fun contributeListFragmentInjector(): RepositoryListFragment
    @ContributesAndroidInjector(modules = arrayOf(RepositoryFragmentsModule::class)) abstract fun contributeDetailsragmentInjector(): RepositoryDetailsFragment
}
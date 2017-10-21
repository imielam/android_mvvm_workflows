/*
 * Copyright (c) 2017. All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.driversweek.base.di.application

import dagger.Module
import dagger.android.ContributesAndroidInjector
import mobilehexers.eu.domain.base.di.ActivitySingleton
import mobilehexers.eu.driversweek.main.MainActivity
import mobilehexers.eu.driversweek.main.dependencyinjection.main.MainFragmentProvider
import mobilehexers.eu.driversweek.repository.RepositoryActivity
import mobilehexers.eu.driversweek.repository.dependencyinjection.RepositoryActivityModule
import mobilehexers.eu.driversweek.repository.dependencyinjection.RepositoryFragmentsProvider
import mobilehexers.eu.driversweek.start.StartActivity

@Module abstract class ActivityBuilderModule {

    @ActivitySingleton
    @ContributesAndroidInjector(modules = arrayOf(MainFragmentProvider::class)) internal abstract fun bindMainActivityInjector(): MainActivity

    @ActivitySingleton
    @ContributesAndroidInjector internal abstract fun bindStartActivityInjector(): StartActivity

    @ActivitySingleton
    @ContributesAndroidInjector(modules = arrayOf(RepositoryFragmentsProvider::class,
            RepositoryActivityModule::class)) internal abstract fun bindRepositoryActivityInjector(): RepositoryActivity

    // Add another builder binding here
}

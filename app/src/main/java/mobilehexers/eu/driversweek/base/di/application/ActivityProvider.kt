/*
 * Copyright (c) 2017.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.driversweek.base.di.application

import com.mobilehexers.driversweek.base.dependencyinjection.module.MainActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector
import mobilehexers.eu.driversweek.main.MainActivity
import mobilehexers.eu.driversweek.main.dependencyinjection.MainFragmentProvider
import mobilehexers.eu.driversweek.repository.RepositoryActivity
import mobilehexers.eu.driversweek.repository.dependencyinjection.RepositoryActivityModule
import mobilehexers.eu.driversweek.repository.dependencyinjection.RepositoryFragmentsProvider
import mobilehexers.eu.driversweek.start.StartActivity

/**
 * Created by mimiela on 10.10.17.
 */
@Module abstract class ActivityProvider {

    @ContributesAndroidInjector abstract fun contributeStartActivityInjector(): StartActivity

    @ContributesAndroidInjector(
            modules = arrayOf(MainActivityModule::class, MainFragmentProvider::class)) abstract fun contributeMainActivityInjector(): MainActivity

    @ContributesAndroidInjector(modules = arrayOf(RepositoryActivityModule::class,
            RepositoryFragmentsProvider::class)) abstract fun contributeRepositoryActivityInjector(): RepositoryActivity
}
/*
 * Copyright (c) 2017. All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.demo.base.di.application

import dagger.Module
import dagger.android.ContributesAndroidInjector
import mobilehexers.eu.domain.base.di.ActivitySingleton
import mobilehexers.eu.demo.main.activity.MainActivity
import mobilehexers.eu.demo.main.activity.di.MainActivityModule
import mobilehexers.eu.demo.main.activity.di.MainFragmentProvider
import mobilehexers.eu.demo.repository.activity.RepositoryActivity
import mobilehexers.eu.demo.repository.activity.di.RepositoryActivityModule
import mobilehexers.eu.demo.repository.activity.di.RepositoryFragmentsProvider
import mobilehexers.eu.demo.start.activity.StartActivity
import mobilehexers.eu.demo.start.activity.di.StartActivityModule
import mobilehexers.eu.demo.start.activity.di.StartFragmentsProvider
import mobilehexers.eu.demo.weather.activity.WeatherActivity
import mobilehexers.eu.demo.weather.activity.di.WetherActivityModule
import mobilehexers.eu.demo.weather.activity.di.WetherFragmentsProvider

@Module
abstract class ActivityBuilderModule {

    @ActivitySingleton
    @ContributesAndroidInjector(modules = [MainFragmentProvider::class, MainActivityModule::class])
    internal abstract fun bindMainActivityInjector(): MainActivity

    @ActivitySingleton
    @ContributesAndroidInjector(modules = [StartFragmentsProvider::class, StartActivityModule::class])
    internal abstract fun bindStartActivityInjector(): StartActivity

    @ActivitySingleton
    @ContributesAndroidInjector(modules = [RepositoryFragmentsProvider::class, RepositoryActivityModule::class])
    internal abstract fun bindRepositoryActivityInjector(): RepositoryActivity

    @ActivitySingleton
    @ContributesAndroidInjector(modules = [WetherFragmentsProvider::class, WetherActivityModule::class])
    internal abstract fun bindWeatherActivityInjector(): WeatherActivity

    // Add another builder binding here
}

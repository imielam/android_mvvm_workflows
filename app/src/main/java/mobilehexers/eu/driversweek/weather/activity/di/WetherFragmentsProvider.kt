/*
 * Copyright (c) 2018.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.driversweek.weather.activity.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import mobilehexers.eu.domain.base.di.FragmentSingleton
import mobilehexers.eu.driversweek.weather.details.WeatherDetailsFragment
import mobilehexers.eu.driversweek.weather.details.di.WeatherDetailsFragmentsModule
import mobilehexers.eu.driversweek.weather.list.CityListFragment
import mobilehexers.eu.driversweek.weather.list.di.WetherListFragmentsModule

@Module
abstract class WetherFragmentsProvider {
    @FragmentSingleton
    @ContributesAndroidInjector(modules = [WetherListFragmentsModule::class])
    abstract fun contributeListFragmentInjector(): CityListFragment

    @FragmentSingleton
    @ContributesAndroidInjector(modules = [WeatherDetailsFragmentsModule::class])
    abstract fun contributeDetailsragmentInjector(): WeatherDetailsFragment
}
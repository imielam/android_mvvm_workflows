/*
 * Copyright (c) 2017. All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.driversweek.base.di.application

import dagger.Module
import dagger.android.ContributesAndroidInjector
import mobilehexers.eu.domain.base.di.ActivitySingleton
import mobilehexers.eu.driversweek.courses.activity.CoursesActivity
import mobilehexers.eu.driversweek.courses.activity.di.CoursesActivityModule
import mobilehexers.eu.driversweek.courses.activity.di.CoursesFragmentsProvider
import mobilehexers.eu.driversweek.main.activity.MainActivity
import mobilehexers.eu.driversweek.main.activity.di.MainActivityModule
import mobilehexers.eu.driversweek.main.activity.di.MainFragmentProvider
import mobilehexers.eu.driversweek.repository.activity.RepositoryActivity
import mobilehexers.eu.driversweek.repository.activity.di.RepositoryActivityModule
import mobilehexers.eu.driversweek.repository.activity.di.RepositoryFragmentsProvider
import mobilehexers.eu.driversweek.start.activity.StartActivity
import mobilehexers.eu.driversweek.start.activity.di.StartActivityModule
import mobilehexers.eu.driversweek.start.activity.di.StartFragmentsProvider
import mobilehexers.eu.driversweek.weather.activity.WeatherActivity
import mobilehexers.eu.driversweek.weather.activity.di.WetherActivityModule
import mobilehexers.eu.driversweek.weather.activity.di.WetherFragmentsProvider

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
    @ContributesAndroidInjector(modules = [CoursesFragmentsProvider::class, CoursesActivityModule::class])
    internal abstract fun bindCourseActivityInjector(): CoursesActivity

    @ActivitySingleton
    @ContributesAndroidInjector(modules = [WetherFragmentsProvider::class, WetherActivityModule::class])
    internal abstract fun bindWeatherActivityInjector(): WeatherActivity

    // Add another builder binding here
}

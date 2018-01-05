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
import mobilehexers.eu.driversweek.start.StartActivity

@Module abstract class ActivityBuilderModule {

    @ActivitySingleton
    @ContributesAndroidInjector(
            modules = arrayOf(MainFragmentProvider::class, MainActivityModule::class)) internal abstract fun bindMainActivityInjector(): MainActivity

    @ActivitySingleton
    @ContributesAndroidInjector internal abstract fun bindStartActivityInjector(): StartActivity

    @ActivitySingleton
    @ContributesAndroidInjector(modules = arrayOf(RepositoryFragmentsProvider::class,
            RepositoryActivityModule::class)) internal abstract fun bindRepositoryActivityInjector(): RepositoryActivity

    @ActivitySingleton
    @ContributesAndroidInjector(modules = arrayOf(CoursesFragmentsProvider::class,
            CoursesActivityModule::class)) internal abstract fun bindCourseActivityInjector(): CoursesActivity

    // Add another builder binding here
}

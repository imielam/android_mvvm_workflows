/*
 * Copyright (c) 2017.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.driversweek.courses.activity.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import mobilehexers.eu.domain.base.di.FragmentSingleton
import mobilehexers.eu.driversweek.courses.list.CoursesListFragment
import mobilehexers.eu.driversweek.courses.list.di.CoursesListFragmentsModule

@Module abstract class CoursesFragmentsProvider {
    @FragmentSingleton
    @ContributesAndroidInjector(modules = arrayOf(CoursesListFragmentsModule::class)) abstract fun contributeListFragmentInjector(): CoursesListFragment
}
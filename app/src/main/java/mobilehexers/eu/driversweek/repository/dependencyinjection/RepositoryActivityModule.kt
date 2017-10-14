/*
 * Copyright (c) 2017.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.driversweek.repository.dependencyinjection

import android.app.Activity
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ActivityKey
import dagger.android.AndroidInjector
import dagger.multibindings.IntoMap
import mobilehexers.eu.domain.rx.SchedulerProvider
import mobilehexers.eu.driversweek.repository.RepositoryActivity
import mobilehexers.eu.driversweek.repository.model.RepositoryModel
import mobilehexers.eu.uibase.base.di.annotation.ActivitySingleton

@Module(subcomponents = arrayOf(RepositoryActivitySubcomponent::class)) abstract class RepositoryActivityModule {

    @Binds
    @IntoMap
    @ActivityKey(RepositoryActivity::class) internal abstract fun bindAndroidInjectorFactory(
            builder: RepositoryActivitySubcomponent.Builder): AndroidInjector.Factory<out Activity>

    abstract val schedulerProvider: SchedulerProvider
}
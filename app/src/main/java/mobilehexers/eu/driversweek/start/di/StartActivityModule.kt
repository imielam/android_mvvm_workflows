/*
 * Copyright (c) 2017.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.driversweek.start.di

import android.app.Activity
import dagger.Binds
import dagger.Module
import dagger.android.ActivityKey
import dagger.android.AndroidInjector
import dagger.multibindings.IntoMap
import mobilehexers.eu.driversweek.start.StartActivity

@Module(subcomponents = arrayOf(StartActivitySubcomponent::class)) abstract class StartActivityModule {
    @Binds
    @IntoMap
    @ActivityKey(StartActivity::class) internal abstract fun bindAndroidInjectorFactory(
            builder: StartActivitySubcomponent.Builder): AndroidInjector.Factory<out Activity>
}
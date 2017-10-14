/*
 * Copyright (c) 2017.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.driversweek.start.di

import dagger.Subcomponent
import dagger.android.AndroidInjector
import mobilehexers.eu.driversweek.start.StartActivity
import mobilehexers.eu.uibase.base.di.annotation.ActivitySingleton

@ActivitySingleton
@Subcomponent()
interface StartActivitySubcomponent : AndroidInjector<StartActivity> {

    @Subcomponent.Builder abstract class Builder : AndroidInjector.Builder<StartActivity>()
}
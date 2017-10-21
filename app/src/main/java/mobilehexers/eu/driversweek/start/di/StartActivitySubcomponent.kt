/*
 * Copyright (c) 2017.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.driversweek.start.di

import dagger.Subcomponent
import dagger.android.AndroidInjector
import mobilehexers.eu.domain.base.di.ActivitySingleton
import mobilehexers.eu.driversweek.start.StartActivity

@ActivitySingleton
@Subcomponent
interface StartActivitySubcomponent : AndroidInjector<StartActivity> {

    @Subcomponent.Builder abstract class Builder : AndroidInjector.Builder<StartActivity>()
}

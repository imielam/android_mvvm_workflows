/*
 * Copyright (c) 2017.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.driversweek.repository.dependencyinjection

import dagger.Subcomponent
import dagger.android.AndroidInjector
import mobilehexers.eu.driversweek.repository.RepositoryActivity
import mobilehexers.eu.uibase.base.di.annotation.ActivitySingleton

@ActivitySingleton
@Subcomponent(modules = arrayOf(RepositoryFragmentsProvider::class))
interface RepositoryActivitySubcomponent : AndroidInjector<RepositoryActivity> {

    @Subcomponent.Builder abstract class Builder : AndroidInjector.Builder<RepositoryActivity>()
}
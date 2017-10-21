/*
 * Copyright (c) 2017.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.driversweek.main.dependencyinjection.main

import dagger.Module
import dagger.Provides
import mobilehexers.eu.domain.base.di.FragmentSingleton
import mobilehexers.eu.domain.base.workflow.Workflow
import mobilehexers.eu.presentation.main.workflow.MainWorkflow

@Module
class MainFragmentModule {
    @Provides
    @FragmentSingleton
    internal fun providesWorkflow(mainWorkflow: MainWorkflow): Workflow = mainWorkflow
}

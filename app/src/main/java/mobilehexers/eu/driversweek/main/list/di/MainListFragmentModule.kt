/*
 * Copyright (c) 2017.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.driversweek.main.list.di

import dagger.Module
import dagger.Provides
import mobilehexers.eu.domain.base.di.FragmentSingleton
import mobilehexers.eu.domain.base.viewmodel.FragmentViewModel
import mobilehexers.eu.domain.base.workflow.Workflow
import mobilehexers.eu.driversweek.main.list.MainListViewModel
import mobilehexers.eu.presentation.main.workflow.MainWorkflow

@Module
class MainListFragmentModule {

    @Provides
    @FragmentSingleton internal fun providesWorkflow(mainWorkflow: MainWorkflow): Workflow = mainWorkflow

    @Provides
    @FragmentSingleton internal fun providesViewModel(workflow: MainWorkflow): FragmentViewModel = MainListViewModel(workflow)
}

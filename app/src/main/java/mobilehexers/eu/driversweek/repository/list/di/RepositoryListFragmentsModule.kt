/*
 * Copyright (c) 2017.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.driversweek.repository.list.di

import dagger.Module
import dagger.Provides
import mobilehexers.eu.domain.base.di.FragmentSingleton
import mobilehexers.eu.domain.base.viewmodel.FragmentViewModel
import mobilehexers.eu.domain.base.workflow.Workflow
import mobilehexers.eu.driversweek.repository.list.RepositoryListViewModel
import mobilehexers.eu.domain.repository.model.RepositoryModel
import mobilehexers.eu.presentation.repository.workflow.RepositoryWorkflow

@Module
class RepositoryListFragmentsModule {

    @Provides
    @FragmentSingleton internal fun providesViewModel(workflow: RepositoryWorkflow, model: RepositoryModel): FragmentViewModel = RepositoryListViewModel(
            workflow, model)

    @Provides
    @FragmentSingleton internal fun providesWorkflow(workflow: RepositoryWorkflow): Workflow = workflow
}
/*
 * Copyright (c) 2017.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.driversweek.repository.dependencyinjection

import dagger.Module
import dagger.Provides
import mobilehexers.eu.domain.base.di.FragmentSingleton
import mobilehexers.eu.domain.base.viewmodel.FragmentViewModel
import mobilehexers.eu.domain.base.workflow.Workflow
import mobilehexers.eu.driversweek.repository.details.RepositoryDetailsViewModel
import mobilehexers.eu.domain.repository.model.RepositoryModel
import mobilehexers.eu.presentation.repository.workflow.RepositoryWorkflow

@Module
class RepositoryDetailsFragmentsModule {
    @Provides
    @FragmentSingleton
    internal fun providesViewModel(model: RepositoryModel): FragmentViewModel = RepositoryDetailsViewModel(model)

    @Provides
    @FragmentSingleton internal fun providesWorkflow(workflow: RepositoryWorkflow): Workflow = workflow
}
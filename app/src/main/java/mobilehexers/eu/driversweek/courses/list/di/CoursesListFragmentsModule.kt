/*
 * Copyright (c) 2017.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.driversweek.courses.list.di

import dagger.Module
import dagger.Provides
import mobilehexers.eu.domain.base.di.FragmentSingleton
import mobilehexers.eu.domain.base.viewmodel.FragmentViewModel
import mobilehexers.eu.domain.base.workflow.Workflow
import mobilehexers.eu.driversweek.courses.list.CoursesListViewModel
import mobilehexers.eu.presentation.courses.workflow.CoursesWorkflow
import mobilehexers.eu.presentation.repository.workflow.RepositoryWorkflow

@Module
class CoursesListFragmentsModule {

    @Provides
    @FragmentSingleton internal fun providesViewModel(workflow: RepositoryWorkflow): FragmentViewModel = CoursesListViewModel(workflow)

    @Provides
    @FragmentSingleton internal fun providesWorkflow(workflow: CoursesWorkflow): Workflow = workflow
}
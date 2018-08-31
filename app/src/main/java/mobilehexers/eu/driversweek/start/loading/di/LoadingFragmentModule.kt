/*
 * Copyright (c) 2018.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.driversweek.start.loading.di

import dagger.Module
import dagger.Provides
import mobilehexers.eu.domain.base.di.FragmentSingleton
import mobilehexers.eu.domain.base.rx.SchedulerProvider
import mobilehexers.eu.domain.base.viewmodel.FragmentViewModel
import mobilehexers.eu.domain.start.StartModel
import mobilehexers.eu.driversweek.start.loading.LoadingViewModel
import mobilehexers.eu.presentation.start.workflow.StartWorkflow

@Module
class LoadingFragmentModule {

    @Provides
    @FragmentSingleton
    internal fun providesViewModel(
            model: StartModel,
            workflow: StartWorkflow,
            schedulerProvider: SchedulerProvider
    ): FragmentViewModel = LoadingViewModel(model, workflow, schedulerProvider)
}
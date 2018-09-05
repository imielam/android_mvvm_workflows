/*
 * Copyright (c) 2018.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.driversweek.weather.list.di

import dagger.Module
import dagger.Provides
import mobilehexers.eu.domain.base.di.FragmentSingleton
import mobilehexers.eu.domain.base.rx.SchedulerProvider
import mobilehexers.eu.domain.base.viewmodel.FragmentViewModel
import mobilehexers.eu.domain.base.workflow.Workflow
import mobilehexers.eu.domain.weather.model.WeatherModel
import mobilehexers.eu.driversweek.weather.list.CityListViewModel
import mobilehexers.eu.presentation.wether.workflow.WeatherWorkflow

@Module
class WetherListFragmentsModule {

    @Provides
    @FragmentSingleton
    internal fun providesViewModel(
            workflow: WeatherWorkflow,
            model: WeatherModel,
            schedulerProvider: SchedulerProvider
    ): FragmentViewModel = CityListViewModel(
            workflow,
            model,
            schedulerProvider
    )

    @Provides
    @FragmentSingleton
    internal fun providesWorkflow(workflow: WeatherWorkflow): Workflow = workflow
}
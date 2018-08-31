/*
 * Copyright (c) 2018.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.presentation.wether.workflow

import mobilehexers.eu.domain.base.rx.SchedulerProvider
import mobilehexers.eu.domain.base.workflow.Workflow

class WeatherWorkflow(schedulerProvider: SchedulerProvider) : Workflow(WeatherState(), schedulerProvider)
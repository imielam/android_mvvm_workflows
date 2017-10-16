/*
 * Copyright (c) 2017.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.presentation.main.workflow

import mobilehexers.eu.domain.base.rx.SchedulerProvider
import mobilehexers.eu.domain.base.workflow.Workflow
import javax.inject.Inject

class MainWorkflow @Inject constructor(schedulerProvider: SchedulerProvider) : Workflow(MainState(), schedulerProvider)
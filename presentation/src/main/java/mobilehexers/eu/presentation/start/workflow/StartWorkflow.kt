/*
 * Copyright (c) 2017.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.presentation.start.workflow

import mobilehexers.eu.domain.base.rx.SchedulerProvider
import mobilehexers.eu.domain.base.workflow.Workflow
import javax.inject.Inject

class StartWorkflow @Inject constructor(schedulerProvider: SchedulerProvider) : Workflow(StartState(), schedulerProvider)
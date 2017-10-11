/*
 * Copyright (c) 2017.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.domain.workflow.start

import mobilehexers.eu.domain.rx.SchedulerProvider
import mobilehexers.eu.domain.workflow.base.Workflow
import javax.inject.Inject

class StartWorkflow @Inject constructor(schedulerProvider: SchedulerProvider) : Workflow(StartState(), schedulerProvider)
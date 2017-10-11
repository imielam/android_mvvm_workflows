/*
 * Copyright (c) 2017.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.domain.workflow.main

import mobilehexers.eu.domain.rx.SchedulerProvider
import mobilehexers.eu.domain.workflow.base.Workflow
import javax.inject.Inject

class MainWorkflow @Inject constructor(schedulerProvider: SchedulerProvider) : Workflow(MainState(), schedulerProvider)
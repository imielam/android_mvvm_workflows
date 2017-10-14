/*
 * Copyright (c) 2017.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.presentation.repository.workflow

import mobilehexers.eu.domain.rx.SchedulerProvider
import mobilehexers.eu.domain.workflow.base.Workflow
import javax.inject.Inject

class RepositoryWorkflow @Inject constructor(schedulerProvider: SchedulerProvider) : Workflow(RepositoryState(), schedulerProvider)
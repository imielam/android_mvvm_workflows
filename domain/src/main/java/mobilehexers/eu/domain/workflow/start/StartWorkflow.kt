package mobilehexers.eu.domain.workflow.start

import mobilehexers.eu.domain.rx.SchedulerProvider
import mobilehexers.eu.domain.workflow.base.Workflow
import javax.inject.Inject

/**
 * Created by mimiela on 26.09.17.
 */
class StartWorkflow @Inject constructor(schedulerProvider: SchedulerProvider) : Workflow(StartState(), schedulerProvider) {
}
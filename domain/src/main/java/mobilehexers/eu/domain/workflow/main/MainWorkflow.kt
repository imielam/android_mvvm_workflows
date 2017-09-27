package mobilehexers.eu.domain.workflow.main

import mobilehexers.eu.domain.workflow.base.Workflow
import javax.inject.Inject

/**
 * Created by mimiela on 26.09.17.
 */
class MainWorkflow @Inject constructor() : Workflow(MainState()) {
}
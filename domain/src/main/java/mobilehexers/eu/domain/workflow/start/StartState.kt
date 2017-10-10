package mobilehexers.eu.domain.workflow.start

import mobilehexers.eu.domain.workflow.base.State
import javax.inject.Inject

/**
 * Created by mimiela on 9/23/17.
 */
class StartState : State{
    var currentState = StartEnum.INITIALIZED
        private set


    override fun next() {
        when (currentState) {
            StartEnum.INITIALIZED -> currentState = StartEnum.ENDED
        //TODO: Add logging for else
        }
    }

    override fun toString(): String {
        return "StartState(currentState=${currentState.name})"
    }

}
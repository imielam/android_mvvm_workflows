package mobilehexers.eu.domain.workflow.main

import mobilehexers.eu.domain.workflow.base.State

/**
 * Created by mimiela on 9/23/17.
 */
class MainState : State {
    var currentState = MainEnum.INITIALIZED
        private set

    override fun next() {
        when (currentState) {
            MainEnum.INITIALIZED -> currentState = MainEnum.MAIN
            MainEnum.MAIN -> currentState = MainEnum.ENDED
            //TODO: Add logging for else
        }
    }
}
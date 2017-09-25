package mobilehexers.eu.domain.workflow

/**
 * Created by mimiela on 9/23/17.
 */
class StartState : State {
    var currentState = StartEnum.INITIALIZED
        private set


    override fun next() {
        when (currentState) {
            StartEnum.INITIALIZED -> currentState = StartEnum.ENDED
            //TODO: Add logging for else
        }
    }

}
/*
 * Copyright (c) 2017.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.presentation.start.workflow

import mobilehexers.eu.domain.base.workflow.State
import mobilehexers.eu.domain.extensions.logTag

class StartState : State {
    var currentState = StartEnum.INITIALIZED
        private set

    override fun next() {
        when (currentState) {
            StartEnum.INITIALIZED -> currentState = StartEnum.WORKING
            StartEnum.WORKING -> currentState = StartEnum.ENDED
            else -> println(logTag + "Unsupported state: " + currentState)
        }
    }

    override fun next(nextState: State) {
        next()
    }

    override fun previous() {
        currentState = StartEnum.ENDED
    }

    override fun reset() {
        currentState = StartEnum.INITIALIZED
    }

    override fun toString() = "StartState(state=${currentState.name})"
}
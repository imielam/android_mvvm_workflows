/*
 * Copyright (c) 2017.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.domain.workflow.start

import mobilehexers.eu.domain.extensions.logTag
import mobilehexers.eu.domain.workflow.base.State

class StartState : State {
    var currentState = StartEnum.INITIALIZED
        private set

    override fun next() {
        when (currentState) {
            StartEnum.INITIALIZED -> currentState = StartEnum.ENDED
            else -> println(logTag + "Unsupported state: " + currentState)
        }
    }

    override fun next(state: State) {
        next()
    }

    override fun previous() {
        currentState = StartEnum.ENDED
    }

    override fun reset() {
        currentState = StartEnum.INITIALIZED
    }

    override fun toString(): String {
        return "StartState(state=${currentState.name})"
    }
}
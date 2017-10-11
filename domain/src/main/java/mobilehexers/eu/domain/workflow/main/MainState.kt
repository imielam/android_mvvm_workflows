/*
 * Copyright (c) 2017.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.domain.workflow.main

import mobilehexers.eu.domain.extensions.logTag
import mobilehexers.eu.domain.workflow.base.State

class MainState : State {
    var currentState = MainEnum.INITIALIZED
        private set

    override fun next() {
        when (currentState) {
            MainEnum.INITIALIZED -> currentState = MainEnum.MAIN
            MainEnum.MAIN -> currentState = MainEnum.ENDED
            else -> println(logTag + "Unsupported state: " + currentState)
        }
    }

    override fun reset() {
        currentState = MainEnum.INITIALIZED
    }

    override fun toString(): String {
        return "MainState(currentState=${currentState.name})"
    }
}
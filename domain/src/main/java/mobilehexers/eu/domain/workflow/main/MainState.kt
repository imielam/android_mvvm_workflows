/*
 * Copyright (c) 2017.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.domain.workflow.main

import mobilehexers.eu.domain.workflow.base.State

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

    override fun toString(): String {
        return "MainState(currentState=${currentState.name})"
    }
}
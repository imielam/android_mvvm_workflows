/*
 * Copyright (c) 2017.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.presentation.main.workflow

import mobilehexers.eu.domain.base.workflow.State
import mobilehexers.eu.domain.extensions.logTag

class MainState(var currentState: MainEnum = MainEnum.INITIALIZED) : State {

    override fun next() {
        when (currentState) {
            MainEnum.INITIALIZED -> currentState = MainEnum.MAIN
            MainEnum.REPOSITORY -> currentState = MainEnum.ENDED
            else -> println(logTag + ": Unsupported state: " + currentState)
        }
    }

    override fun next(nextState: State) {
        if (nextState is MainState) {
            val nextStateEnum = nextState.currentState
            when (currentState) {
            //FIXME: Check if nextStateEnum is one of type to start independent
                MainEnum.MAIN -> currentState = nextStateEnum
                else -> println(logTag + ": Unsupported state: " + currentState)
            }
        } else {
            println(logTag + String.format(": Cannot use custom state transition to %s, from %s", nextState, currentState))
        }
    }

    override fun previous() {
        when (currentState) {
            MainEnum.REPOSITORY -> currentState = MainEnum.MAIN
            MainEnum.MAIN -> currentState = MainEnum.ENDED
            else -> println(logTag + ": Unsupported state: " + currentState)
        }
    }

    override fun reset() {
        currentState = MainEnum.INITIALIZED
    }

    override fun toString() = "MainState(state=${currentState.name})"
}
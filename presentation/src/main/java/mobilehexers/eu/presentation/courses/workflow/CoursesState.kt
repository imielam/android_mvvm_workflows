/*
 * Copyright (c) 2017.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.presentation.courses.workflow

import mobilehexers.eu.domain.base.workflow.State
import mobilehexers.eu.domain.extensions.logTag

class CoursesState(var currentState: CoursesEnum = CoursesEnum.INITIALIZED) : State {

    override fun next() {
        when (currentState) {
            CoursesEnum.INITIALIZED -> currentState = CoursesEnum.LIST
            CoursesEnum.LIST -> currentState = CoursesEnum.ENDED
            else -> println(logTag + "Unsupported state: " + currentState)
        }
    }

    override fun next(nextState: State) {
//        if (nextState is CoursesState) {
//            val nextStateEnum = nextState.currentState
//            when (currentState) {
//            //FIXME: Check if nextStateEnum is one of type to start independent
//                CoursesEnum.MAIN -> currentState = nextStateEnum
//                else -> println(logTag + "Unsupported state: " + currentState)
//            }
//        } else {
//            println(logTag + String.format("Cannot use custom state transition to %s, from %s", nextState, currentState))
//        }
    }

    override fun previous() {
        currentState = CoursesEnum.ENDED
    }

    override fun reset() {
        currentState = CoursesEnum.INITIALIZED
    }

    override fun toString() = "MainState(state=${currentState.name})"
}
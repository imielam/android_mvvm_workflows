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
            else -> println("$logTag: Unsupported state: $currentState")
        }
    }

    override fun next(nextState: State) {
        if (nextState is CoursesState) {
            currentState= nextState.currentState
        } else {
            println("$logTag: Cannot use custom state transition to $nextState, from $currentState")
        }
    }

    override fun previous() {
        currentState = CoursesEnum.ENDED
    }

    override fun reset() {
        currentState = CoursesEnum.INITIALIZED
    }

    override fun toString() = "CoursesState(state=${currentState.name})"
}
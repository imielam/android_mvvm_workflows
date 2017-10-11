/*
 * Copyright (c) 2017.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.presentation.repository.workflow

import mobilehexers.eu.domain.extensions.logTag
import mobilehexers.eu.domain.workflow.base.State

class RepositoryState : State {
    var currentState = RepositoryEnum.INITIALIZED
        private set

    override fun next() {
        when (currentState) {
            RepositoryEnum.INITIALIZED -> currentState = RepositoryEnum.LIST
            RepositoryEnum.LIST -> currentState = RepositoryEnum.DETAILS
            else -> println(logTag + "Unsupported state: " + currentState)
        }
    }

    override fun reset() {
        currentState = RepositoryEnum.INITIALIZED
    }

    override fun toString(): String {
        return "MainState(currentState=${currentState.name})"
    }
}
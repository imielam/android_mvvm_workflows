/*
 * Copyright (c) 2018.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.presentation.wether.workflow

import mobilehexers.eu.domain.base.workflow.State
import mobilehexers.eu.domain.extensions.logTag

class WeatherState : State {
    var currentState = WeatherEnum.INITIALIZED
        private set

    override fun next() {
        when (currentState) {
            WeatherEnum.INITIALIZED -> currentState = WeatherEnum.LIST
            WeatherEnum.LIST -> currentState = WeatherEnum.DETAILS
            else -> println(logTag + "Unsupported state: " + currentState)
        }
    }

    override fun next(state: State) {
        next()
    }

    override fun previous() {
        when (currentState) {
            WeatherEnum.DETAILS -> currentState = WeatherEnum.LIST
            WeatherEnum.LIST -> currentState = WeatherEnum.ENDED
            else -> println(logTag + "Unsupported state: " + currentState)
        }
    }

    override fun reset() {
        currentState = WeatherEnum.INITIALIZED
    }

    override fun toString(): String {
        return "WeatherState(state=${currentState.name})"
    }
}
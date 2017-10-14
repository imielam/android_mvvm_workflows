/*
 * Copyright (c) 2017.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.domain.workflow.base

interface State {
    fun reset()
    fun next()
    fun next(nextState: State)
    fun previous()
}
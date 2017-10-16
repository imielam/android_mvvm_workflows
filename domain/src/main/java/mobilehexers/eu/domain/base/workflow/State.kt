/*
 * Copyright (c) 2017.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.domain.base.workflow

interface State {
    fun reset()
    fun next()
    fun next(nextState: State)
    fun previous()
}
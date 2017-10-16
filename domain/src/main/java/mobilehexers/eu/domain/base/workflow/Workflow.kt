/*
 * Copyright (c) 2017.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.domain.base.workflow

import io.reactivex.disposables.Disposable
import io.reactivex.functions.Action
import io.reactivex.functions.Consumer
import io.reactivex.processors.ReplayProcessor
import mobilehexers.eu.domain.base.rx.SchedulerProvider
import mobilehexers.eu.domain.extensions.logTag

abstract class Workflow(private var state: State, private val schedulerProvider: SchedulerProvider) {

    private val processor = ReplayProcessor.createWithSize<State>(1)!!

    fun init(next: Consumer<State>, error: Consumer<Throwable>, complete: Action): Disposable {
        return processor.subscribeOn(schedulerProvider.ioThread).observeOn(schedulerProvider.mainThread).subscribe(next, error, complete)
    }

    fun next() {
        state.next()
        emitState()
    }

    fun next(nextState: State) {
        state.next(nextState)
        emitState()
    }

    fun previous() {
        state.previous()
        emitState()
    }

    private fun emitState() {
        println(logTag + " emitting: " + state)
        processor.onNext(state)
    }

    fun end() {
        state.reset()
    }

    override fun toString() = "Workflow(state=$state)"
}
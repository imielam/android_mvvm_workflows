/*
 * Copyright (c) 2017.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.domain.workflow.base

import io.reactivex.disposables.Disposable
import io.reactivex.functions.Action
import io.reactivex.functions.Consumer
import io.reactivex.processors.ReplayProcessor
import mobilehexers.eu.domain.extensions.logTag
import mobilehexers.eu.domain.rx.SchedulerProvider

abstract class Workflow(private val state: State, private val schedulerProvider: SchedulerProvider) {

    private val processor = ReplayProcessor.createWithSize<State>(1)!!

    fun init(next: Consumer<State>, error: Consumer<Throwable>, complete: Action): Disposable {
        println(logTag + " processor.hasSubscribers: " + processor.hasSubscribers())
        val disposable = processor.subscribeOn(schedulerProvider.ioThread).observeOn(schedulerProvider.mainThread).subscribe(next, error, complete)
        println(logTag + " processor.hasSubscribers: " + processor.hasSubscribers())
        return disposable
    }

    fun next() {
        state.next()
        println(logTag + " state: " + state)
        processor.onNext(state)
    }

    override fun toString(): String {
        return "Workflow(state=$state)"
    }
}
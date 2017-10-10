package mobilehexers.eu.domain.workflow.base

import io.reactivex.disposables.Disposable
import io.reactivex.functions.Action
import io.reactivex.functions.Consumer
import io.reactivex.processors.PublishProcessor
import mobilehexers.eu.domain.rx.SchedulerProvider

/**
 * Created by mimiela on 9/22/17.
 */
abstract class Workflow(private val state: State, private val schedulerProvider: SchedulerProvider) {
    private val processor = PublishProcessor.create<State>()!!

    fun next() {
        state.next()
        processor.onNext(state)
    }

    fun init(next: Consumer<State>, error: Consumer<Throwable>, complete: Action): Disposable =
            processor.
                    subscribeOn(schedulerProvider.computationThread).
                    observeOn(schedulerProvider.mainThread).
                    subscribe(next, error, complete)

    override fun toString(): String {
        return "Workflow(state=$state)"
    }


}
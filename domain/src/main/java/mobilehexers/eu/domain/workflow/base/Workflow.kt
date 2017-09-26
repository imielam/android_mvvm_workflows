package mobilehexers.eu.domain.workflow.base

import io.reactivex.disposables.Disposable
import io.reactivex.functions.Action
import io.reactivex.functions.Consumer
import io.reactivex.processors.PublishProcessor
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by mimiela on 9/22/17.
 */
open class Workflow constructor(private val state: State) {
    private val processor = PublishProcessor.create<State>()!!

    fun next() {
        state.next()
        processor.onNext(state)
    }

    fun init(next: Consumer<State>, error: Consumer<Throwable>, complete: Action): Disposable = processor.subscribe(next, error, complete)

}
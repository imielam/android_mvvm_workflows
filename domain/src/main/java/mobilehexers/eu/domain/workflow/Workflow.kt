package mobilehexers.eu.domain.workflow

import io.reactivex.disposables.Disposable
import io.reactivex.functions.Action
import io.reactivex.functions.Consumer
import io.reactivex.processors.PublishProcessor
import javax.inject.Inject

/**
 * Created by mimiela on 9/22/17.
 */
class Workflow {
    private val processor = PublishProcessor.create<State>()!!
    @Inject private lateinit var state: State

    fun next() {
        state.next()
        processor.onNext(state)
    }

    fun init(next: Consumer<State>, error: Consumer<Throwable>, complete: Action): Disposable {
        state = StartState()
        return processor.subscribe(next, error, complete)
    }
}
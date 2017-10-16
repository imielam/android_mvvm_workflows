/*
 * Copyright (c) 2017.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.domain.base.viewmodel

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class FragmentViewModel {

    private val disposables: CompositeDisposable = CompositeDisposable()

    fun onDetach() {
        disposeSubscriptions()
    }

    abstract fun onViewCreated()

    private fun disposeSubscriptions() {
        disposables.clear()
    }

    protected fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }
}
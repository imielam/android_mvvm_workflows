/*
 * Copyright (c) 2018.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.driversweek.start.loading

import android.util.Log
import mobilehexers.eu.domain.base.rx.SchedulerProvider
import mobilehexers.eu.domain.base.viewmodel.FragmentViewModel
import mobilehexers.eu.domain.extensions.logTag
import mobilehexers.eu.domain.start.StartModel
import mobilehexers.eu.presentation.start.workflow.StartWorkflow

class LoadingViewModel(
        private val model: StartModel,
        private val workflow: StartWorkflow,
        private val schedulerProvider: SchedulerProvider
) : FragmentViewModel() {
    override fun onViewCreated() {
        addDisposable(model.initDataBase()
                .subscribeOn(schedulerProvider.ioThread)
                .observeOn(schedulerProvider.mainThread)
                .subscribe(
                        { workflow.next() },
                        { Log.e(logTag, it.localizedMessage) }
                )
        )
    }
}
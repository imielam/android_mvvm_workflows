/*
 * Copyright (c) 2017.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.driversweek.repository.details

import android.databinding.ObservableField
import android.databinding.ObservableInt
import android.util.Log
import android.view.View
import mobilehexers.eu.domain.viewmodel.FragmentViewModel
import mobilehexers.eu.driversweek.extensions.logTag
import mobilehexers.eu.driversweek.repository.model.RepositoryModel
import mobilehexers.eu.presentation.repository.workflow.RepositoryWorkflow
import mobilehexers.eu.uibase.base.di.annotation.FragmentSingleton
import javax.inject.Inject

@FragmentSingleton
class RepositoryDetailsViewModel @Inject constructor() : FragmentViewModel() {

    val details = ObservableField<RepositoryDetailsItem>()
    val detailsVisibility = ObservableInt()
    val progressVisibility = ObservableInt()

    @Inject lateinit var workflow: RepositoryWorkflow
    @Inject lateinit var model: RepositoryModel

    override fun onViewCreated() {
        detailsVisibility.set(View.GONE)
        progressVisibility.set(View.VISIBLE)
        addDisposable(
                model.getRepositoryDetails().subscribe({ next -> showNextItem(next) }, { throwable: Throwable -> Log.e(logTag, throwable.localizedMessage) }))
    }

    private fun showNextItem(next: RepositoryDetailsItem) {
        progressVisibility.set(View.GONE)
        detailsVisibility.set(View.VISIBLE)
        details.set(next)
    }
}
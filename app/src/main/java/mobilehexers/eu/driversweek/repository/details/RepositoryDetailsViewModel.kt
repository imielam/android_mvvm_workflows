/*
 * Copyright (c) 2017.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.driversweek.repository.details

import android.databinding.ObservableField
import android.databinding.ObservableInt
import android.util.Log
import android.view.View
import mobilehexers.eu.domain.base.viewmodel.FragmentViewModel
import mobilehexers.eu.domain.extensions.logTag
import mobilehexers.eu.domain.repository.details.entity.RepositoryDetailsItem
import mobilehexers.eu.domain.repository.model.RepositoryModel
import javax.inject.Inject

class RepositoryDetailsViewModel @Inject constructor(private val model: RepositoryModel) : FragmentViewModel() {

    val details = ObservableField<RepositoryDetailsItem>()
    val detailsVisibility = ObservableInt()
    val progressVisibility = ObservableInt()

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

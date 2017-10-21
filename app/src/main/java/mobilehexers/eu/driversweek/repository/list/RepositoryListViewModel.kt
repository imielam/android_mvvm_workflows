/*
 * Copyright (c) 2017.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.driversweek.repository.list

import android.util.Log
import io.reactivex.Observable
import mobilehexers.eu.domain.base.viewmodel.FragmentViewModel
import mobilehexers.eu.domain.base.workflow.Workflow
import mobilehexers.eu.domain.extensions.logTag
import mobilehexers.eu.domain.repository.list.entity.RepositoryListItem
import mobilehexers.eu.driversweek.repository.model.RepositoryModel
import javax.inject.Inject

class RepositoryListViewModel @Inject constructor(private val workflow: Workflow, private val model: RepositoryModel) : FragmentViewModel() {

    private val DEFAULT_REPOSITORY_LIMIT = 20

    lateinit var filterObservable: Observable<String>
    lateinit var limitObservable: Observable<Boolean>
    lateinit var listAdapter: RepositoryListAdapter

    private val dataSet by lazy { model.getNewRepositoryListDataSet(listAdapter) }

    override fun onViewCreated() {
        bindRestView()
        addDisposable(model.getRepositoryList().subscribe({ next -> dataSet.addRepositories(next) }, { e -> handleError(e) }))
    }

    private fun bindRestView() {
        addDisposable(filterObservable.filter { text -> text.isNotBlank() }.subscribe({ text -> filterRepositoryList(text) }))
        addDisposable(filterObservable.filter { text -> text.isBlank() }.subscribe({ _ -> removeFilterFromRepositoryList() }))
        addDisposable(limitObservable.subscribe({ status -> limitRepositories(status) }))
    }

    private fun removeFilterFromRepositoryList() {
        Log.v(logTag, "Text empty. Remove filter")
        dataSet.removeFilter()
    }

    private fun filterRepositoryList(text: String) {
        Log.v(logTag, "Text changed. Current text: " + text)
        dataSet.filterRepositories(text)
    }

    private fun handleError(e: Throwable) {
        Log.e(logTag, e.localizedMessage)
    }

    fun repositoryItemClicked(item: RepositoryListItem) {
        model.setRepositoryItemClicked(item)
        showDetails()
    }

    private fun showDetails() {
        workflow.next()
    }

    private fun limitRepositories(selected: Boolean) {
        Log.v(logTag, "Checkbox clicked. Selection: " + selected)
        if (selected) {
            dataSet.limitRepositoriesTo(DEFAULT_REPOSITORY_LIMIT)
        } else {
            dataSet.removeLimit()
        }
    }
}

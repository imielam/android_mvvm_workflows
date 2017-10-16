/*
 * Copyright (c) 2017.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.driversweek.repository.list

import android.util.Log
import io.reactivex.Observable
import mobilehexers.eu.domain.viewmodel.FragmentViewModel
import mobilehexers.eu.driversweek.extensions.logTag
import mobilehexers.eu.driversweek.repository.model.RepositoryModel
import mobilehexers.eu.presentation.repository.workflow.RepositoryWorkflow
import mobilehexers.eu.uibase.base.di.annotation.FragmentSingleton
import javax.inject.Inject

@FragmentSingleton
class RepositoryListViewModel @Inject constructor() : FragmentViewModel() {

    @Inject lateinit var model: RepositoryModel
    @Inject lateinit var workflow: RepositoryWorkflow

    lateinit var filterObservable: Observable<String>
    lateinit var limitObservable: Observable<Boolean>
    lateinit var listAdapter: RepositoryListAdapter

    private val DEFAULT_REPOSITORY_LIMIT = 20

    override fun onViewCreated() {
        bindRestView()
        addDisposable(model.getRepositoryList().subscribe({ next -> listAdapter.addRepositories(next) }, { e -> handleError(e) }))
    }

    private fun bindRestView() {
        addDisposable(filterObservable.filter { text -> text.isNotBlank() }.subscribe({ text -> filterRepositoryList(text) }))
        addDisposable(filterObservable.filter { text -> text.isBlank() }.subscribe({ _ -> removeFilterFromRepositoryList() }))
        addDisposable(limitObservable.subscribe({ status -> limitRepositories(status) }))
    }

    private fun removeFilterFromRepositoryList() {
        Log.v(logTag, "Text empty. Remove filter")
        listAdapter.removeFilter()
    }

    private fun filterRepositoryList(text: String) {
        Log.v(logTag, "Text changed. Current text: " + text)
        listAdapter.filterRepositories(text)
    }

    private fun handleError(e: Throwable) {
        Log.e(logTag, e.localizedMessage)
        //        Snackbar.make(repository_list_container, e.localizedMessage, Snackbar.LENGTH_SHORT).show()
    }

    fun repositoryItemClicked(item: RepositoryListItem) {
        model.repositoryItemClicked = item
        showDetails()
    }

    private fun showDetails() {
        workflow.next()
    }

    private fun limitRepositories(selected: Boolean) {
        Log.v(logTag, "Checkbox clicked. Selection: " + selected)
        if (selected) {
            listAdapter.limitRepositoriesTo(DEFAULT_REPOSITORY_LIMIT)
        } else {
            listAdapter.removeLimit()
        }
    }
}

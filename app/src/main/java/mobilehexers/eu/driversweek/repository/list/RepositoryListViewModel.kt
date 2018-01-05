/*
 * Copyright (c) 2017.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.driversweek.repository.list

import android.util.Log
import io.reactivex.Observable
import me.tatarka.bindingcollectionadapter2.ItemBinding
import me.tatarka.bindingcollectionadapter2.collections.DiffObservableList
import mobilehexers.eu.domain.base.recycler.ViewType
import mobilehexers.eu.domain.base.viewmodel.FragmentViewModel
import mobilehexers.eu.domain.base.workflow.Workflow
import mobilehexers.eu.domain.extensions.logTag
import mobilehexers.eu.domain.repository.list.entity.RepositoryListItem
import mobilehexers.eu.domain.repository.model.RepositoryModel
import mobilehexers.eu.driversweek.BR
import mobilehexers.eu.driversweek.R
import mobilehexers.eu.uibase.base.recycler.RecyclerViewOnItemClickListener
import javax.inject.Inject

class RepositoryListViewModel @Inject constructor(private val workflow: Workflow, private val model: RepositoryModel) : FragmentViewModel() {

    private val DEFAULT_REPOSITORY_LIMIT = 20

    lateinit var filterObservable: Observable<String>
    lateinit var limitObservable: Observable<Boolean>

    val items: DiffObservableList<RepositoryListItem> = DiffObservableList(object : DiffObservableList.Callback<RepositoryListItem> {
        override fun areContentsTheSame(oldItem: RepositoryListItem, newItem: RepositoryListItem) = oldItem.name == newItem.name

        override fun areItemsTheSame(oldItem: RepositoryListItem, newItem: RepositoryListItem) = oldItem == newItem
    })

    val itemBinding = ItemBinding.of<RepositoryListItem>(BR.item, R.layout.item_repository_list).bindExtra(BR.listener,
            object : RecyclerViewOnItemClickListener {
                override fun onItemClick(item: ViewType) {
                    repositoryItemClicked(item as RepositoryListItem)
                }
            })

    private val dataSet = model.getNewRepositoryListDataSet()

    override fun onViewCreated() {
        bindRestView()
        addDisposable(model.getRepositoryList().subscribe({ next -> addRepositories(next) }, { e -> handleError(e) }))
    }

    private fun addRepositories(repositories: List<RepositoryListItem>) {
        Log.v(logTag, "Add repositories: " + repositories)
        val data = dataSet.addRepositories(repositories)
        updateList(data)
    }

    private fun bindRestView() {
        addDisposable(filterObservable.filter { text -> text.isNotBlank() }.subscribe({ text -> filterRepositoryList(text) }))
        addDisposable(filterObservable.filter { text -> text.isBlank() }.subscribe({ _ -> removeFilterFromRepositoryList() }))
        addDisposable(limitObservable.subscribe({ status -> limitRepositories(status) }))
    }

    private fun removeFilterFromRepositoryList() {
        if (!items.isEmpty()) {
            Log.v(logTag, "Text empty. Remove filter")
            val data = dataSet.removeFilter()
            updateList(data)
        }
    }

    private fun filterRepositoryList(text: String) {
        Log.v(logTag, "Text changed. Current text: " + text)
        val data = dataSet.filterRepositories(text)
        updateList(data)
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
        val data = if (selected) {
            dataSet.limitRepositoriesTo(DEFAULT_REPOSITORY_LIMIT)
        } else {
            dataSet.removeLimit()
        }
        updateList(data)
    }

    private fun updateList(data: List<RepositoryListItem>) {
        val diffResult = items.calculateDiff(data)
        items.update(data, diffResult)
    }
}

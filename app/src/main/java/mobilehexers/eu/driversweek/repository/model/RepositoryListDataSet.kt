/*
 * Copyright (c) 2017.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.driversweek.repository.model

import mobilehexers.eu.driversweek.repository.list.RepositoryListAdapter
import mobilehexers.eu.driversweek.repository.list.RepositoryListItem

class RepositoryListDataSet(private val adapter: RepositoryListAdapter) {

    private var allRepositoryItems = mutableListOf<RepositoryListItem>()
    private var filtered = false
    private var filterText = ""
    private var limited = false
    private var limitValue: Int = allRepositoryItems.size

    fun addRepositories(repositories: List<RepositoryListItem>) {
        allRepositoryItems.addAll(repositories)
        adapter.updateWith(getData())
    }

    private fun getData(): List<RepositoryListItem> {
        var data: List<RepositoryListItem> = allRepositoryItems
        if (limited) {
            data = limitData(data, limitValue)
        }
        if (filtered) {
            data = filterData(data, filterText)
        }
        return data
    }

    fun limitRepositoriesTo(max: Int) {
        limited = true
        limitValue = max
        adapter.updateWith(getData())
    }

    fun removeLimit() {
        limited = false
        limitValue = allRepositoryItems.size
        adapter.updateWith(getData())
    }

    fun filterRepositories(text: String) {
        filtered = true
        filterText = text
        adapter.updateWith(getData())
    }

    fun removeFilter() {
        filtered = false
        filterText = ""
        adapter.updateWith(getData())
    }

    private fun filterData(repositoriesList: List<RepositoryListItem>, text: String): List<RepositoryListItem> = repositoriesList.filter {
        it.name.contains(text)
    }.toMutableList()

    private fun limitData(repositoriesList: List<RepositoryListItem>, max: Int) = repositoriesList.subList(0, minOf(max, repositoriesList.size))
}
/*
 * Copyright (c) 2017.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.domain.repository.model

import mobilehexers.eu.domain.repository.list.entity.RepositoryListItem

class RepositoryListDataSet {

    private var allRepositoryItems = mutableListOf<RepositoryListItem>()
    private var filtered = false
    private var filterText = ""
    private var limited = false
    private var limitValue = allRepositoryItems.size

    fun addRepositories(repositories: List<RepositoryListItem>): List<RepositoryListItem> {
        allRepositoryItems.addAll(repositories)
        return getData()
    }

    fun limitRepositoriesTo(max: Int): List<RepositoryListItem> {
        limited = true
        limitValue = max
        return getData()
    }

    fun removeLimit(): List<RepositoryListItem> {
        limited = false
        limitValue = allRepositoryItems.size
        return getData()
    }

    fun filterRepositories(text: String): List<RepositoryListItem> {
        filtered = true
        filterText = text
        return getData()
    }

    fun removeFilter(): List<RepositoryListItem> {
        filtered = false
        filterText = ""
        return getData()
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

    private fun filterData(repositoriesList: List<RepositoryListItem>, text: String): List<RepositoryListItem> = repositoriesList.filter {
        it.name.contains(text)
    }

    private fun limitData(repositoriesList: List<RepositoryListItem>, max: Int) = repositoriesList.subList(0, minOf(max, repositoriesList.size))
}
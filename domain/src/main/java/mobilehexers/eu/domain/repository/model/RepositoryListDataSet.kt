/*
 * Copyright (c) 2017.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.domain.repository.model

import mobilehexers.eu.domain.base.model.ApplicationDataSet
import mobilehexers.eu.domain.repository.list.entity.RepositoryListItem

class RepositoryListDataSet(private val applicationDataSet: ApplicationDataSet) {

    private var allRepositoryItems = mutableListOf<RepositoryListItem>()
    private var filtered = false
    private var filterText = ""
    private var limited = false
    private var limitValue: Int = allRepositoryItems.size

    fun addRepositories(repositories: List<RepositoryListItem>) {
        allRepositoryItems.addAll(repositories)
        applicationDataSet.updateWith(getData())
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
        applicationDataSet.updateWith(getData())
    }

    fun removeLimit() {
        limited = false
        limitValue = allRepositoryItems.size
        applicationDataSet.updateWith(getData())
    }

    fun filterRepositories(text: String) {
        filtered = true
        filterText = text
        applicationDataSet.updateWith(getData())
    }

    fun removeFilter() {
        filtered = false
        filterText = ""
        applicationDataSet.updateWith(getData())
    }

    private fun filterData(repositoriesList: List<RepositoryListItem>, text: String): List<RepositoryListItem> = repositoriesList.filter {
        it.name.contains(text)
    }.toMutableList()

    private fun limitData(repositoriesList: List<RepositoryListItem>, max: Int) = repositoriesList.subList(0, minOf(max, repositoriesList.size))
}
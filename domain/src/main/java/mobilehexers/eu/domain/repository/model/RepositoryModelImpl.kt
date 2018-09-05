/*
 * Copyright (c) 2017.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.domain.repository.model

import io.reactivex.Observable
import mobilehexers.eu.domain.repository.details.entity.RepositoryDetailsItem
import mobilehexers.eu.domain.repository.list.entity.RepositoryListItem
import mobilehexers.eu.domain.repository.manager.RepositoryManager
import javax.inject.Inject

class RepositoryModelImpl @Inject constructor(private val repositoryManager: RepositoryManager) : RepositoryModel {

    lateinit var repositoryItem: RepositoryListItem

    override fun setRepositoryItemClicked(repositoryListItemClicked: RepositoryListItem) {
        repositoryItem = repositoryListItemClicked
    }

    override fun getRepositoryDetails(): Observable<RepositoryDetailsItem> = repositoryManager.getRepositoryDetail(repositoryName = repositoryItem.name)
    override fun getRepositoryList() = repositoryManager.getRepositoryList()
    override fun getNewRepositoryListDataSet(): RepositoryListDataSet = RepositoryListDataSet()
}

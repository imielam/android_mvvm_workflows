/*
 * Copyright (c) 2017.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.driversweek.repository.model

import io.reactivex.Observable
import mobilehexers.eu.data.repository.manager.RepositoryManager
import mobilehexers.eu.domain.base.model.ApplicationDataSet
import mobilehexers.eu.domain.repository.details.entity.RepositoryDetailsItem
import mobilehexers.eu.domain.repository.list.entity.RepositoryListItem
import mobilehexers.eu.domain.repository.model.RepositoryListDataSet
import javax.inject.Inject

//FIXME: Move to domain after add of abstraction layer to RepositoryManager
class RepositoryModelImpl @Inject constructor(private val repositoryManager: RepositoryManager) : RepositoryModel {

    lateinit var repositoryItem: RepositoryListItem

    override fun setRepositoryItemClicked(repositoryListItemClicked: RepositoryListItem) {
        repositoryItem = repositoryListItemClicked
    }

    override fun getRepositoryDetails(): Observable<RepositoryDetailsItem> = repositoryManager.getRepositoryDetail(repositoryName = repositoryItem.name)
    override fun getRepositoryList() = repositoryManager.getRepositoryList()
    override fun getNewRepositoryListDataSet(applicationDataSet: ApplicationDataSet): RepositoryListDataSet = RepositoryListDataSet(applicationDataSet)
}

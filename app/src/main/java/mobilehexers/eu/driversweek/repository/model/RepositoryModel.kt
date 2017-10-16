/*
 * Copyright (c) 2017.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.driversweek.repository.model

import io.reactivex.Observable
import mobilehexers.eu.data.repository.manager.RepositoryManager
import mobilehexers.eu.domain.base.di.ActivitySingleton
import mobilehexers.eu.domain.base.model.ApplicationDataSet
import mobilehexers.eu.domain.repository.details.entity.RepositoryDetailsItem
import mobilehexers.eu.domain.repository.list.entity.RepositoryListItem
import mobilehexers.eu.domain.repository.model.RepositoryListDataSet
import javax.inject.Inject

//FIXME: Move to domain after add of abstraction layer to RepositoryManager
@ActivitySingleton
class RepositoryModel @Inject constructor() {

    @Inject lateinit var repositoryManager: RepositoryManager
    lateinit var repositoryItemClicked: RepositoryListItem

    fun getRepositoryDetails(): Observable<RepositoryDetailsItem> = repositoryManager.getRepositoryDetail(repositoryName = repositoryItemClicked.name)
    fun getRepositoryList() = repositoryManager.getRepositoryList()
    fun getNewRepositoryListDataSet(applicationDataSet: ApplicationDataSet): RepositoryListDataSet = RepositoryListDataSet(applicationDataSet)
}
/*
 * Copyright (c) 2017.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.driversweek.repository.model

import io.reactivex.Observable
import mobilehexers.eu.driversweek.repository.details.RepositoryDetailsItem
import mobilehexers.eu.driversweek.repository.list.RepositoryListItem
import mobilehexers.eu.driversweek.repository.manager.RepositoryManager
import mobilehexers.eu.uibase.base.di.annotation.ActivitySingleton
import javax.inject.Inject

@ActivitySingleton
class RepositoryModel @Inject constructor() {

    @Inject lateinit var repositoryManager: RepositoryManager
    lateinit var repositoryItemClicked: RepositoryListItem

    fun getRepositoryDetails(): Observable<RepositoryDetailsItem> = repositoryManager.getRepositoryDetail(repositoryName = repositoryItemClicked.name)
}
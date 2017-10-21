/*
 * Copyright (c) 2017.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.driversweek.repository.model

import io.reactivex.Observable
import mobilehexers.eu.domain.base.model.ApplicationDataSet
import mobilehexers.eu.domain.repository.details.entity.RepositoryDetailsItem
import mobilehexers.eu.domain.repository.list.entity.RepositoryListItem
import mobilehexers.eu.domain.repository.model.RepositoryListDataSet

interface RepositoryModel {

    fun getRepositoryDetails(): Observable<RepositoryDetailsItem>
    fun getRepositoryList(): Observable<List<RepositoryListItem>>
    fun getNewRepositoryListDataSet(applicationDataSet: ApplicationDataSet): RepositoryListDataSet
    fun setRepositoryItemClicked(repositoryListItem: RepositoryListItem)
}
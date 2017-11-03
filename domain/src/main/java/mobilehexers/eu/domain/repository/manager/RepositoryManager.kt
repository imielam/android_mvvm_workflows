/*
 * Copyright (c) 2017.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.domain.repository.manager

import io.reactivex.Observable
import mobilehexers.eu.domain.repository.details.entity.RepositoryDetailsItem
import mobilehexers.eu.domain.repository.list.entity.RepositoryListItem

interface RepositoryManager {
    private companion object {
        val defaultUserName = "square"
        val defaultRepositoryName = "retrofit"
    }

    fun getRepositoryList(username: String = defaultUserName): Observable<List<RepositoryListItem>>
    fun getRepositoryDetail(username: String = defaultUserName, repositoryName: String = defaultRepositoryName): Observable<RepositoryDetailsItem>
}
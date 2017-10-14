/*
 * Copyright (c) 2017.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.driversweek.repository.model

import mobilehexers.eu.driversweek.repository.list.RepositoryListItem
import mobilehexers.eu.uibase.base.di.annotation.ActivitySingleton
import javax.inject.Inject

@ActivitySingleton
class RepositoryModel constructor(var repositoryItemClicked: RepositoryListItem) {
    @Inject
    constructor():
        this(RepositoryListItem(""))
}
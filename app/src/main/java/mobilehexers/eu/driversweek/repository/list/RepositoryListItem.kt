/*
 * Copyright (c) 2017.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.driversweek.repository.list

import mobilehexers.eu.domain.recycler.ViewType
import mobilehexers.eu.uibase.base.recycler.AdapterConstants

data class RepositoryListItem(var name: String): ViewType {
    override fun getViewType() = AdapterConstants.REPOSITORY_ITEM
}
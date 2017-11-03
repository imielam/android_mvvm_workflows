/*
 * Copyright (c) 2017.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.uibase.base.recycler

import mobilehexers.eu.domain.repository.list.entity.RepositoryListItem

interface RecyclerViewOnItemClickListener {
    fun onItemClick(item: RepositoryListItem)
}
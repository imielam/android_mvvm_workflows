/*
 * Copyright (c) 2017.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.uibase.base.recycler

import mobilehexers.eu.domain.recycler.ViewType

interface RecyclerViewOnItemClickListener {
    fun onItemClick(item: ViewType)
}
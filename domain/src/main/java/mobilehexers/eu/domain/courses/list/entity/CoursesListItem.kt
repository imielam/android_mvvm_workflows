/*
 * Copyright (c) 2017.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.domain.courses.list.entity

import mobilehexers.eu.domain.base.recycler.AdapterConstants
import mobilehexers.eu.domain.base.recycler.ViewType

data class CoursesListItem(var name: String) : ViewType {
    override fun getViewType() = AdapterConstants.ITEM
}
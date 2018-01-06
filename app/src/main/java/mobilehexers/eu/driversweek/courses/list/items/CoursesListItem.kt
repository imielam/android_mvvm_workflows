/*
 * Copyright (c) 2017.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.driversweek.courses.list.items

import mobilehexers.eu.domain.base.recycler.AdapterConstants
import mobilehexers.eu.domain.base.recycler.ViewType

data class CoursesListItem(val name: String, val description: String) : ViewType {
    override fun getViewType() = AdapterConstants.ITEM
}
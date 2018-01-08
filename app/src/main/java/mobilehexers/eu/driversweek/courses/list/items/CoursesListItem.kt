/*
 * Copyright (c) 2017.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.driversweek.courses.list.items

import android.databinding.ObservableBoolean
import mobilehexers.eu.domain.base.recycler.AdapterConstants
import mobilehexers.eu.domain.base.recycler.ViewType
import mobilehexers.eu.domain.courses.list.entity.CoursesListEntity

data class CoursesListItem(val id: Long, val name: String, val description: String) : ViewType {
    val selected = ObservableBoolean(false)

    override fun getViewType() = AdapterConstants.ITEM

    fun convertToEntity() = CoursesListEntity(id, name, description)
}
/*
 * Copyright (c) 2017.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.domain.courses.model

import mobilehexers.eu.domain.courses.list.entity.CoursesListEntity
import mobilehexers.eu.domain.courses.manager.CoursesManager
import javax.inject.Inject

class CoursesModel @Inject constructor(private val coursesManager: CoursesManager) {
    fun getCourses() = coursesManager.getCourses()
    fun makeBooking(list: List<CoursesListEntity>) = coursesManager.makeBooking(list)

}

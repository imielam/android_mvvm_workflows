/*
 * Copyright (c) 2017.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.domain.courses.manager

import io.reactivex.Single
import mobilehexers.eu.domain.courses.list.entity.CoursesListEntity

interface CoursesManager {
    fun getCourses(): Single<List<CoursesListEntity>>
    fun makeBooking(list: List<CoursesListEntity>): Single<Boolean>
}
/*
 * Copyright (c) 2017.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.data.courses.api

import mobilehexers.eu.data.courses.data.CoursesBookingRequest
import mobilehexers.eu.data.courses.data.CoursesDetailResponse
import retrofit2.Call
import retrofit2.http.*

internal interface CoursesApi {
    @GET("classes")
    fun getClasses(): Call<List<CoursesDetailResponse>>


    @POST("bookings")
    fun makeBooking(@Body coursesId: CoursesBookingRequest): Call<Any>
}

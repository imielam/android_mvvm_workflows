/*
 * Copyright (c) 2017.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.data.courses.api

import mobilehexers.eu.data.courses.data.CoursesBookingRequest
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class CoursesRestAPI {
    private val baseUrl = "https://private-anon-9c12b8b6a2-senfinohr1.apiary-mock.com/"
    private val coursesApi: CoursesApi by lazy {
        val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(initLogInterceptor())
                .build()
        val retrofit = Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(MoshiConverterFactory.create()).client(okHttpClient).build()

        retrofit.create(CoursesApi::class.java)
    }

    private fun initLogInterceptor() = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

    internal fun getCoursesList() = coursesApi.getClasses()
    internal fun makeBooking(coursesId: CoursesBookingRequest) = coursesApi.makeBooking(coursesId)
}
/*
 * Copyright (c) 2017.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.data.courses.api

import mobilehexers.eu.data.base.retrofit.LogInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class CoursesRestAPI {
    private val baseUrl = "https://private-anon-9c12b8b6a2-senfinohr1.apiary-mock.com/"
    private val coursesApi: CoursesApi by lazy {
        val okHttpClient = OkHttpClient.Builder().addInterceptor(LogInterceptor()).build()
        val retrofit = Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(MoshiConverterFactory.create()).client(okHttpClient).build()

        retrofit.create(CoursesApi::class.java)
    }

    fun getCoursesList() = coursesApi.getClasses()
}
/*
 * Copyright (c) 2017.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.data.courses.api

import mobilehexers.eu.data.courses.response.CoursesDetailResponse
import mobilehexers.eu.data.repository.response.GithubRepositoryDetailsResponse
import mobilehexers.eu.data.repository.response.GithubRepositoryResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

internal interface CoursesApi {
    @GET("classes")
    fun getClasses(): Call<List<CoursesDetailResponse>>
}

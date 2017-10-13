/*
 * Copyright (c) 2017.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.data.repository.api

import mobilehexers.eu.data.repository.GithubRepositoryResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubApi {
    @GET("users/{user}/repos")
    fun getRepos(@Path("user") user: String): Call<List<GithubRepositoryResponse>>
}

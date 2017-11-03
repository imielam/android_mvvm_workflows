/*
 * Copyright (c) 2017.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.data.repository.api

import mobilehexers.eu.data.repository.response.GithubRepositoryDetailsResponse
import mobilehexers.eu.data.repository.response.GithubRepositoryResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

internal interface GithubApi {
    @GET("users/{user}/repos")
    fun getRepositoryList(@Path("user") user: String): Call<List<GithubRepositoryResponse>>

    @GET("repos/{user}/{repository_name}")
    fun getRepositoryDetails(@Path("user") user: String, @Path("repository_name") repositoryName: String): Call<GithubRepositoryDetailsResponse>
}

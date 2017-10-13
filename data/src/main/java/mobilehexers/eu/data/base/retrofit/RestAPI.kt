/*
 * Copyright (c) 2017.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.data.base.retrofit

import mobilehexers.eu.data.repository.GithubRepositoryResponse
import mobilehexers.eu.data.repository.api.GithubApi
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class RestAPI {
    private val baseUrl = "https://api.github.com/"
    private val githubApi: GithubApi by lazy {
        val okHttpClient = OkHttpClient.Builder().addInterceptor(LogInterceptor()).build()
        val retrofit = Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(MoshiConverterFactory.create()).client(okHttpClient).build()

        retrofit.create(GithubApi::class.java)
    }

    fun getRepositoryList(username: String): Call<List<GithubRepositoryResponse>> = githubApi.getRepos(username)
}
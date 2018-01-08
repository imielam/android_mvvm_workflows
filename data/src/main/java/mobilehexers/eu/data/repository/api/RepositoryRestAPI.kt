/*
 * Copyright (c) 2017.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.data.repository.api

import mobilehexers.eu.data.repository.response.GithubRepositoryDetailsResponse
import mobilehexers.eu.data.repository.response.GithubRepositoryResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class RepositoryRestAPI {
    private val baseUrl = "https://api.github.com/"
    private val githubApi: GithubApi by lazy {
        val okHttpClient = OkHttpClient.Builder().addInterceptor(initLogInterceptor()).build()
        val retrofit = Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(MoshiConverterFactory.create()).client(okHttpClient).build()

        retrofit.create(GithubApi::class.java)
    }

    private fun initLogInterceptor() = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

    fun getRepositoryList(username: String): Call<List<GithubRepositoryResponse>> = githubApi.getRepositoryList(username)
    fun getRepositoryDetails(username: String, repositoryName: String): Call<GithubRepositoryDetailsResponse> = githubApi.getRepositoryDetails(username,
            repositoryName)
}
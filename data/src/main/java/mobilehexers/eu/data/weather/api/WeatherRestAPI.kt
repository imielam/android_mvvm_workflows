/*
 * Copyright (c) 2018.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.data.weather.api

import io.reactivex.Single
import mobilehexers.eu.data.weather.response.WeatherApiData
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

class WeatherRestAPI {
    private val baseUrl = "http://api.openweathermap.org/data/2.5/"
    private val mOpenWeatherApi: OpenWeatherApi by lazy {
        val okHttpClient = OkHttpClient.Builder().addInterceptor(initLogInterceptor()).build()
        val retrofit = Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(MoshiConverterFactory.create()).addCallAdapterFactory(
                RxJava2CallAdapterFactory.create()).client(okHttpClient).build()

        retrofit.create(OpenWeatherApi::class.java)
    }

    private fun initLogInterceptor() = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

    fun getWeatherDataFor(id: Long): Single<WeatherApiData> = mOpenWeatherApi.getWeatherDataFor(id)
}
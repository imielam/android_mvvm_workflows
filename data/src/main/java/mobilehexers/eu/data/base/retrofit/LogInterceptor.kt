/*
 * Copyright (c) 2017.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.data.base.retrofit

import android.util.Log
import mobilehexers.eu.data.extensions.logTag

import java.io.IOException

import okhttp3.Interceptor
import okhttp3.Response

/**
 * Created by mimiela on 14.10.17.
 */
internal class LogInterceptor : Interceptor {

    @Throws(IOException::class) override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val t1 = System.nanoTime()
        Log.d(logTag,String.format("Sending request %s on %s%n%s", request.url(), chain.connection(), request.headers()))

        val response = chain.proceed(request)

        val t2 = System.nanoTime()
        Log.d(logTag,String.format("Received response for %s in %.1fms%n%s", response.request().url(), (t2 - t1) / 1e6, response.headers()))

        return response
    }
}

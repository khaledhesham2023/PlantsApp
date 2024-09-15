package com.khaledamin.plantsapp.util

import com.khaledamin.plantsapp.BuildConfig
import com.khaledamin.plantsapp.datasource.remote.Api
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun provideRetrofitInstance() : Retrofit {

    val client: OkHttpClient = OkHttpClient().newBuilder().addInterceptor {
        val request = it.request()
        val url = request.url().toString()
        if (url.contains("plants")) {
            val newRequest =
                request.newBuilder().addHeader("Authorization", BuildConfig.API_KEY).build()
            it.proceed(newRequest)
        } else {
            it.proceed(request)
        }
    }.build()

    return Retrofit.Builder()
        .baseUrl(Api.BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}
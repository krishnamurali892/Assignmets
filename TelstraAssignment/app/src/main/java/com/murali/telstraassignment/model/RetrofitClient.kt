package com.murali.telstraassignment.model

import com.murali.telstraassignment.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private const val BASE_URL = "https://dl.dropboxusercontent.com/s/2iodh4vg0eortkl/"

    // Create logger interceptor
    private val logger = HttpLoggingInterceptor().setLevel(
        if(BuildConfig.DEBUG)
            HttpLoggingInterceptor.Level.BODY
         else
            HttpLoggingInterceptor.Level.NONE)

    // Create okHttp client
    private val okHttp = OkHttpClient.Builder().addInterceptor(logger)

    // Create retrofit builder
    private val builder =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttp.build())

    //create retrofit instance
    private val retrofit = builder.build()

    fun <T> buildService(serviceType: Class<T>): T{
        return retrofit.create(serviceType)
    }
}
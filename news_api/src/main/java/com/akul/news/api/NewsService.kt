package com.akul.news.api

import com.akul.news.api.data.Articles
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.http.GET

interface NewsService {

    @GET("/v2/top-headlines")
    suspend fun topHeadLines(): Articles
}

fun NewsService(apiKey: String): NewsService {
    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val authorizedRequest = chain.request().newBuilder()
                .addHeader(HEADER_API_KEY, apiKey)
                .build()

            chain.proceed(authorizedRequest)
        }
        .build()

    val retrofit = Retrofit.Builder()
        .baseUrl("https://newsapi.org/")
        .client(okHttpClient)
        .addConverterFactory(Json.asConverterFactory(MediaType.parse("application/json")!!))
        .build()

    return retrofit.create(NewsService::class.java)
}

private const val HEADER_API_KEY = "X-Api-Key"
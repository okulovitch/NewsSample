package com.akul.news

import android.app.Application
import com.akul.news.di.AppComponent
import com.akul.news.di.DaggerAppComponent

class NewsApp : Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
            .application(this)
            .apiKey(BuildConfig.NEWS_API_KEY)
            .build()
    }

    override fun onCreate() {
        super.onCreate()
    }
}
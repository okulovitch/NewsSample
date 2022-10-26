package com.akul.news.details

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel

class NewsDetailsComponentViewModel(application: Application) : AndroidViewModel(application) {
    val newsDetailsComponent: NewsDetailsComponent by lazy {
        DaggerNewsDetailsComponent.builder()
            .deps(application.newsDetailsDepsProvider.deps)
            .build()
    }
}
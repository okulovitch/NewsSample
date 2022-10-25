package com.akul.news.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akul.news.api.NewsService
import com.akul.news.api.data.Article
import kotlinx.coroutines.flow.*

class ArticlesViewModel(private val newsService: NewsService) : ViewModel() {
//todo realize NewsDetailsDepth in AppComponent of Application
//    val newsDetailsComponent: NewsDetailsComponent by lazy {
//        DaggerNewsDetailsComponent.builder()
//            .deps()
//            .build()
//    }

    val articles = flow<List<Article>> {
        newsService.topHeadLines().articles
    }.stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())
}
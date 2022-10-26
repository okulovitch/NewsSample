package com.akul.news.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.akul.news.api.NewsService
import com.akul.news.api.data.Article
import dagger.Provides
import kotlinx.coroutines.flow.*
import javax.inject.Inject
import javax.inject.Provider

internal class ArticlesViewModel(private val newsService: NewsService) : ViewModel() {

    val articles = flow {
        emit(newsService.topHeadLines().articles)
    }.stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

    class Factory @Inject constructor(
        private val newsService: Provider<NewsService>
    ) : ViewModelProvider.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            require(modelClass == ArticlesViewModel::class.java)
            return ArticlesViewModel(newsService.get()) as T
        }
    }
}
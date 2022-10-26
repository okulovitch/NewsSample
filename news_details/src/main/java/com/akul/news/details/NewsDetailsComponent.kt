package com.akul.news.details

import android.app.Application
import android.content.Context
import com.akul.news.api.NewsService
import dagger.Component
import dagger.Module
import javax.inject.Scope

@[NewsDetailsScope Component(
    dependencies = [NewsDetailsDeps::class],
    modules = [NewsDetailsModule::class]
)]
interface NewsDetailsComponent {
    fun inject(fragment: ArticlesFragment)

    @Component.Builder
    interface Builder {

        fun deps(newsDetailsDeps: NewsDetailsDeps): Builder

        fun build(): NewsDetailsComponent
    }
}

@Module
internal class NewsDetailsModule {

}

interface NewsDetailsDepsProvider {

    val deps: NewsDetailsDeps
}

interface NewsDetailsDeps {
    val newsService: NewsService
}

val Context.newsDetailsDepsProvider: NewsDetailsDepsProvider
    get() = when (this) {
        is NewsDetailsDepsProvider -> this
        is Application -> error("Application must implements NewsDetailsDepsProvider")
        else -> applicationContext.newsDetailsDepsProvider
    }

@Scope
internal annotation class NewsDetailsScope
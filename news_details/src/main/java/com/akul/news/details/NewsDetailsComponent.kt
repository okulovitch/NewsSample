package com.akul.news.details

import com.akul.news.api.NewsService
import dagger.Component
import dagger.Module
import javax.inject.Scope

@[NewsDetailsScope Component(
    dependencies = [NewsDetailsDeps::class],
    modules = [NewsDetailsModule::class]
)]
interface NewsDetailsComponent {

    @Component.Builder
    interface Builder {

        fun deps(newsDetailsDeps: NewsDetailsDeps): Builder

        fun build(): NewsDetailsComponent
    }
}

@Module
internal class NewsDetailsModule {

}

interface NewsDetailsDeps {
    val newsService: NewsService
}

@Scope
annotation class NewsDetailsScope
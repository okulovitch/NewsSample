package com.akul.news.di

import android.app.Application
import com.akul.news.api.NewsService
import com.akul.news.details.NewsDetailsDeps
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Qualifier
import javax.inject.Scope

@[AppScope Component(modules = [AppModule::class])]
interface AppComponent: NewsDetailsDeps {

    override val newsService: NewsService
        get() = TODO("Not yet implemented")

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        @BindsInstance
        fun apiKey(@NewsApiQualifier apikey: String): Builder

        fun build(): AppComponent
    }
}

@Module
class AppModule {
    @Provides
    @AppScope
    fun provideNewsService(@NewsApiQualifier apiKey: String): NewsService {
        return NewsService(apiKey)
    }
}

@Qualifier
annotation class NewsApiQualifier

@Scope
annotation class AppScope
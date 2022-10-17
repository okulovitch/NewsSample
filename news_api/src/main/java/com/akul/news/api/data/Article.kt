package com.akul.news.api.data

import com.akul.news.api.utils.DateSerializer
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class Articles(
    val articles: List<Article>
)
@Serializable
data class Article(
    val source:Source,
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val urlImage: String?,
    @Serializable(with = DateSerializer::class) val publishedAt: Date,
    val content: String
)
@Serializable
data class Source(
    val id: String,
    val name: String
)

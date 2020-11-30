package com.rempeople.news.data.models

data class Headline (
        val status: String,
        val totalResults: Long,
        val articles: MutableList<Article>
)
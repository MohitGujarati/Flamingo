package com.example.flamingo.data.model

data class NewsMainModel(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)
package com.example.flamingo.data.model

data class MovieMainModelItem(
    val rating: Float,
    val title: String,
    val poster: String,
    val overview: String,
    val genres: List<String>
)
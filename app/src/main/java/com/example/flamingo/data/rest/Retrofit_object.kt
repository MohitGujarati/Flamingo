package com.example.flamingo.data.rest

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Retrofit_object {

    private val newsretrofit by lazy {
        Retrofit.Builder()
            .baseUrl(News_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private val moviewretrofit by lazy {
        Retrofit.Builder()
            .baseUrl(Movie_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val getNewsApi by lazy {
        newsretrofit.create(Retrofit_interface::class.java)
    }

    val getMovieApi by lazy {
        moviewretrofit.create(Retrofit_interface::class.java)
    }

}
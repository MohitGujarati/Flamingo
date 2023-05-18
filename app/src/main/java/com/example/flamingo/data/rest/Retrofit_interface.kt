package com.example.flamingo.data.rest

import com.example.flamingo.data.model.MovieMainModel
import com.example.flamingo.data.model.MovieMainModelItem
import com.example.flamingo.data.model.NewsMainModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


const val News_BASE_URL = "https://newsapi.org/"
const val News_API_KEY = "6566c770e39349c1b7f924d0bc85a62f"
const val NEWS_API = "v2/top-headlines?country=in&apiKey=$News_API_KEY"

const val Movie_BASE_URL = "https://mocki.io/"
const val Movie_API_KEY = "588be1a7-9c66-4af1-bcc0-63fa249e5b9f"
const val Movie_API = "v1/$Movie_API_KEY"

const val QueryNews_Api = "https://newsapi.org/"


interface Retrofit_interface {

        @GET("v2/everything")
        fun getTopHeadlines(
            @Query("q") query: String,
            @Query("apiKey") apiKey: String
        ): Call<NewsMainModel>


    @GET(NEWS_API)
    fun getnews(): retrofit2.Call<NewsMainModel>

    @GET(Movie_API)
    fun getmovie(): retrofit2.Call<List<MovieMainModelItem>>
}
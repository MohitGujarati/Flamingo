package com.example.flamingo.data.rest

import com.example.flamingo.data.model.MovieMainModel
import com.example.flamingo.data.model.MovieMainModelItem
import com.example.flamingo.data.model.NewsMainModel
import retrofit2.Call
import retrofit2.http.GET


const val News_BASE_URL = "https://newsapi.org/"
const val News_API_KEY = "6566c770e39349c1b7f924d0bc85a62f"
const val NEWS_API = "v2/top-headlines?country=in&apiKey=$News_API_KEY"

const val Movie_BASE_URL = "https://mocki.io/v1/"
const val Movie_API_KEY = "588be1a7-9c66-4af1-bcc0-63fa249e5b9f"
const val Movie_API = "https://mocki.io/v1/&apiKey=$Movie_API_KEY"



interface Retrofit_interface {

    @GET(NEWS_API)
    fun getnews(): retrofit2.Call<NewsMainModel>

    @GET("588be1a7-9c66-4af1-bcc0-63fa249e5b9f")
    fun getmovie():retrofit2.Call<List<MovieMainModelItem>>
}
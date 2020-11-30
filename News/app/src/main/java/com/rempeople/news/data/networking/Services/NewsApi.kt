package com.rempeople.news.data.models.network.Services

import com.rempeople.news.data.models.Headline
import com.rempeople.news.data.networking.Constants
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface NewsApi {
    @GET("top-headlines")
    fun getTopHeadlines(@Query(value = "country") country : String,
                        @Query(value = "apiKey") apiKey: String = Constants.apiKey): Call<Headline>

    @GET("everything")
    fun getNewswithQuerry(@Query(value = "q") query : String,
                        @Query(value = "apiKey") apiKey: String = Constants.apiKey): Call<Headline>
}
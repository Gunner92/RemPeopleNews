package com.rempeople.news.data.models.network

import com.rempeople.news.data.networking.Constants
import com.rempeople.news.data.networking.helpers.NetworkConnectionInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ApiClient {
    companion object {

        private val retrofit : Retrofit
            get() {
                val oktHttpClient = OkHttpClient.Builder()
                    .addInterceptor(NetworkConnectionInterceptor())

                return Retrofit.Builder()
                    .baseUrl(Constants.baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(oktHttpClient.build())
                    .build()
            }

        fun <S> createService(serviceClass: Class<S>?): S {
            return retrofit.create(serviceClass)
        }
    }

}
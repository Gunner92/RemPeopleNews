package com.rempeople.news.data.repositories

import androidx.lifecycle.MutableLiveData;
import com.rempeople.news.data.models.BaseResponseModel
import com.rempeople.news.data.models.Headline
import com.rempeople.news.data.models.network.ApiClient
import com.rempeople.news.data.models.network.Services.NewsApi

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class NewsRepository {

    private var newsApi : NewsApi

    constructor(){
        newsApi = ApiClient.createService(serviceClass = NewsApi::class.java)
    }

    fun getNewsHeadlines(country: String): MutableLiveData<BaseResponseModel<Headline>> {
        var newsData = MutableLiveData<BaseResponseModel<Headline>>()
        newsApi.getTopHeadlines(country).enqueue(object:Callback<Headline>{
            override fun onResponse(call: Call<Headline>, response: Response<Headline>) {
                if (response.isSuccessful){
                    newsData.value = BaseResponseModel<Headline>(response.body()!!,null)
                }
            }
            override fun onFailure(call: Call<Headline>, t: Throwable) {
                newsData.value = BaseResponseModel<Headline>(null,t)
            }
        })
        return  newsData
    }

    fun getNewswithQuery(query: String): MutableLiveData<BaseResponseModel<Headline>> {
        var newsData = MutableLiveData<BaseResponseModel<Headline>>()
        newsApi.getNewswithQuerry(query).enqueue(object:Callback<Headline>{
            override fun onResponse(call: Call<Headline>, response: Response<Headline>) {
                if (response.isSuccessful){
                    newsData.value = BaseResponseModel<Headline>(response.body()!!,null)
                }
            }
            override fun onFailure(call: Call<Headline>, t: Throwable) {
                newsData.value = BaseResponseModel<Headline>(null,t)
            }
        })
        return  newsData
    }

    companion object {
        private lateinit var newsRepository: NewsRepository
        fun getInstance(): NewsRepository {
            if (!this::newsRepository.isInitialized) {
                newsRepository = NewsRepository()
            }
            return newsRepository;
        }
    }
}
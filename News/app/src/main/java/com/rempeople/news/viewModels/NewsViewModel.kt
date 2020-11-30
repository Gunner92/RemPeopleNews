package com.rempeople.news.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rempeople.news.data.models.BaseResponseModel
import com.rempeople.news.data.models.Headline
import com.rempeople.news.data.repositories.NewsRepository

class NewsViewModel : ViewModel() {
    private lateinit var mutableLiveData: MutableLiveData<BaseResponseModel<Headline>>
    private lateinit var newsRepository: NewsRepository

    public fun initialize() {
        if (this::mutableLiveData.isInitialized && mutableLiveData != null)
        {
            return;
        }
        newsRepository = NewsRepository.getInstance()
    }
    fun getNewsRepository(country:String): LiveData<BaseResponseModel<Headline>> {
        mutableLiveData = newsRepository.getNewsHeadlines(country)
        return  mutableLiveData
    }

    fun getNewsWithQuerry(query: String): LiveData<BaseResponseModel<Headline>> {
        mutableLiveData = newsRepository.getNewswithQuery(query)
        return  mutableLiveData
    }
}
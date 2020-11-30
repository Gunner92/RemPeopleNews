package com.rempeople.news.views.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.rempeople.news.R
import com.rempeople.news.data.models.Article
import com.rempeople.news.databinding.ItemNewsHeadlineBinding
import com.rempeople.news.views.viewHolders.NewsViewHolder

class NewsAdapter (val articles: MutableList<Article> , private val itemClickListener: (Article) -> Unit) : RecyclerView.Adapter<NewsViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news_headline, parent,false)
        return  NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        var  article = articles.get(position)
        holder.bindItems(article)
        holder.itemView.setOnClickListener( View.OnClickListener {
            itemClickListener(article)
        })
    }

    override fun getItemCount(): Int {
        return  articles.count()
    }
}
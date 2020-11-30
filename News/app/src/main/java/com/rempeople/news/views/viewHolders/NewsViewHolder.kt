package com.rempeople.news.views.viewHolders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewParent
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rempeople.news.R
import com.rempeople.news.data.models.Article
import com.rempeople.news.views.helpers.DateFormat

class NewsViewHolder (view: View) : RecyclerView.ViewHolder(view){
    var newsHeader :TextView = view.findViewById(R.id.tv_NewsHeader)
    var newsDate :TextView = view.findViewById(R.id.tv_NewsDate)
    var newsSource :TextView = view.findViewById(R.id.tv_NewsSource)

    fun bindItems(item : Article) {
        newsHeader.text = item.title
        newsDate.text = DateFormat.formatDate(item.publishedAt)
        newsSource.text = item.source.name

        itemView.setOnClickListener(View.OnClickListener {
        })
    }
}
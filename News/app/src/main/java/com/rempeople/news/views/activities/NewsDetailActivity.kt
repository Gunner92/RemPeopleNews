package com.rempeople.news.views.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.View.*
import androidx.core.view.isVisible
import com.rempeople.news.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_news_detail_activity.*


class NewsDetailActivity : BaseActivity() {

    var title: String? = ""
    var imageUrl: String? = ""
    var description: String? = ""
    var newsUrl: String? = ""
    var content: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_detail_activity)

        imageUrl = intent.getStringExtra("imageUrl")
        description = intent.getStringExtra("description")
        newsUrl = intent.getStringExtra("newsUrl")
        content = intent.getStringExtra("content")
        title = intent.getStringExtra("title")

        setTitle(title)
        loadContent()
    }

    private fun loadContent(): Unit {
        pb_picasso.visibility = View.VISIBLE
        Picasso.get().load(imageUrl).into(iv_newsImage, object: com.squareup.picasso.Callback {
            override fun onSuccess() {
                //set animations here
                if (pb_picasso != null) {
                    pb_picasso.visibility = View.GONE;
                }
            }

            override fun onError(e: java.lang.Exception?) {
                //do smth when there is picture loading error
            }
        })

        tv_NewsDesc.text = description
        tv_NewsUrl.text = newsUrl

        if (content == null || content == "")
        {
            tv_NewsContent.visibility =  View.GONE
            tv_NewsContentHeader.visibility =  View.GONE
        }
        else{
            tv_NewsContent.visibility =  View.VISIBLE
            tv_NewsContentHeader.visibility =  View.VISIBLE
            tv_NewsContent.text = content
        }
    }
}
package com.rempeople.news.views.customViews

import android.app.Dialog
import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.os.Bundle
import android.view.Window
import com.rempeople.news.R

class NewsLoading(context: Context) : Dialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.progress_news)
        window?.setBackgroundDrawableResource(R.color.transparent)
        setCancelable(false)
    }
}
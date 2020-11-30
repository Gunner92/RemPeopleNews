package com.rempeople.news.views.activities

import android.app.Activity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.rempeople.news.views.customViews.NewsLoading

open class BaseActivity : AppCompatActivity() {

    lateinit var loadingDialog : NewsLoading

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
    }

    fun showServiceError(error: Throwable?) {
        runOnUiThread {
            Toast.makeText(this, error?.message, Toast.LENGTH_LONG).show()
        }

    }

    fun manageLoading(show: Boolean): Unit {
        if (!this::loadingDialog.isInitialized){
            loadingDialog = NewsLoading(this)
        }

        if (show){
            loadingDialog.show()
        }
        else{
            loadingDialog.dismiss()
        }
    }

    open fun hideKeyboard(activity: Activity) {
        val imm: InputMethodManager =
            activity.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        //Find the currently focused view, so we can grab the correct window token from it.
        var view: View? = activity.currentFocus
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = View(activity)
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0)
    }
}
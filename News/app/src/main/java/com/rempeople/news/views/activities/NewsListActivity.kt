package com.rempeople.news.views.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.rempeople.news.R
import com.rempeople.news.data.models.Article
import com.rempeople.news.data.models.BaseResponseModel
import com.rempeople.news.data.models.Headline
import com.rempeople.news.viewModels.NewsViewModel
import com.rempeople.news.views.adapters.NewsAdapter
import kotlinx.android.synthetic.main.activity_news_list_activity.*


class NewsListActivity : BaseActivity() {
    lateinit var viewModel: NewsViewModel
    var articleArrayList: ArrayList<Article> = ArrayList()
    var newsAdapter: NewsAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_news_list_activity)

        viewModel = ViewModelProviders.of(this).get(NewsViewModel::class.java)
        viewModel.initialize()

        sv_NewsHeadlines.isIconifiedByDefault = false

        setUpRecyclerView()
        initListeners()
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onResume() {
        super.onResume()
        if (sv_NewsHeadlines.query.toString() == null || sv_NewsHeadlines.query.toString() == "")
        {
            loadTopHeadLines()
        }
        else{
            searchNews(sv_NewsHeadlines.query.toString())
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.mi_sortListAuthor ->{
                var sortedList = articleArrayList.sortedByDescending { it.author }
                articleArrayList.clear()
                articleArrayList.addAll(sortedList)
                newsAdapter!!.notifyDataSetChanged()
                return  true
            }
            R.id.mi_sortListDate ->{
                var sortedList = articleArrayList.sortedByDescending { it.publishedAt }
                articleArrayList.clear()
                articleArrayList.addAll(sortedList)
                newsAdapter!!.notifyDataSetChanged()
                return  true
            }
        }
        return  super.onOptionsItemSelected(item)
    }

    private fun loadTopHeadLines() {
        manageLoading(show = true)
        viewModel.getNewsRepository("us").observe(
            this,
            Observer<BaseResponseModel<Headline>> { baseResponseModel ->
                handleServiceResult(baseResponseModel)
            })
    }

    private fun searchNews(query: String, moveToTop: Boolean = false) {
        manageLoading(show = true)
        viewModel.getNewsWithQuerry(query).observe(
            this,
            Observer<BaseResponseModel<Headline>> { baseResponseModel ->
                handleServiceResult(baseResponseModel, moveToTop)
            })
    }

    private fun handleServiceResult(
        baseResponseModel: BaseResponseModel<Headline>,
        moveToTop: Boolean = false
    ): Unit {
        if (baseResponseModel?.response != null && baseResponseModel.response?.articles != null){
            val newsArticles: List<Article> = baseResponseModel.response?.articles!!
            var sortedNewsArticles = newsArticles.sortedByDescending { it.publishedAt }
            articleArrayList.clear()
            articleArrayList.addAll(sortedNewsArticles)
            newsAdapter!!.notifyDataSetChanged()
        }
        else{
            showServiceError(baseResponseModel.error)
        }
        if (moveToTop)
        {
            rv_NewsHeadlines.smoothScrollToPosition(0);
        }
        manageLoading(show = false)
    }

    private fun initListeners() {
        sv_NewsHeadlines.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchNews(query!!, true)
                sv_NewsHeadlines.clearFocus()
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                if (newText == "" || newText == null) {
                    loadTopHeadLines()
                }
                return true
            }
        })
    }

    private fun setUpRecyclerView() {
        newsAdapter = NewsAdapter(articleArrayList){ item ->
            val intent = Intent(this@NewsListActivity, NewsDetailActivity::class.java)
            intent.putExtra("imageUrl", item.urlToImage)
            intent.putExtra("description", item.description)
            intent.putExtra("newsUrl", item.url)
            intent.putExtra("content", item.content)
            intent.putExtra("title", item.title)
            startActivity(intent)
        }
        rv_NewsHeadlines.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false
        )
        rv_NewsHeadlines.adapter = newsAdapter
    }
}

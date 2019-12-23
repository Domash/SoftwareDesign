package com.domash.rssreader

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.prof.rssparser.Article
import com.prof.rssparser.Parser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


class ArticlesViewModel : ViewModel() {

    private var url = ""
    private lateinit var articleListLive: MutableLiveData<MutableList<Article>>

    private val viewModelJob = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    fun getArticleList(): MutableLiveData<MutableList<Article>> {
        if (!::articleListLive.isInitialized) {
            articleListLive = MutableLiveData()
        }
        return articleListLive
    }

    private fun setArticleList(articleList: MutableList<Article>) {
        articleListLive.postValue(articleList)
    }

    fun setUrl(url : String) {
        this.url = url
    }

    fun getUrl(): String {
        return url
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun fetchFeed() {

        coroutineScope.launch(Dispatchers.Main) {
            try {
                val parser = Parser()
                val articleList = parser.getArticles(url)
                setArticleList(articleList)
            } catch (e: Exception) {
                e.printStackTrace()
                setArticleList(mutableListOf())
            }
        }

    }

}
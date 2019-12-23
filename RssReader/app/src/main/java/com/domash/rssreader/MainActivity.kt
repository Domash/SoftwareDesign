package com.domash.rssreader

import android.content.BroadcastReceiver
import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.domash.rssreader.network.NetworkReceiver
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import kotlinx.android.synthetic.main.activity_main.*
import android.view.Menu
import android.view.MenuItem
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import android.widget.EditText

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: ArticleAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var swipeLayout: SwipeRefreshLayout
    private lateinit var articlesViewModel: ArticlesViewModel
    private lateinit var networkReceiver: BroadcastReceiver

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        networkReceiver = NetworkReceiver()
        registerReceiver(networkReceiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))

        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.setHasFixedSize(true)

        articlesViewModel = ViewModelProviders.of(this@MainActivity).get(ArticlesViewModel::class.java)

        if(articlesViewModel.getUrl() == "") requestRssFeed()

        articlesViewModel.getArticleList().observe(this, Observer { articles ->

            if (articles != null) {
                adapter = ArticleAdapter(articles)
                recyclerView.adapter = adapter
                adapter.notifyDataSetChanged()
                progressBar.visibility = View.GONE
                swipeLayout.isRefreshing = false
            }

        })

        swipeLayout = findViewById(R.id.swipe_layout)
        swipeLayout.setColorSchemeResources(R.color.colorPrimary, R.color.colorPrimaryDark)
        swipeLayout.canChildScrollUp()
        swipeLayout.setOnRefreshListener {
            adapter.articles.clear()
            adapter.notifyDataSetChanged()
            swipe_layout.isRefreshing = true
            articlesViewModel.fetchFeed()
        }

        val networkStatus = NetworkReceiver.getStatus(this)

        if (networkStatus == NetworkReceiver.NetworkStatus.NOT_CONNECTED) {

        } else {
            articlesViewModel.fetchFeed()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val id = item.itemId

        if(id == R.id.menu_set_feed) {
            requestRssFeed()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun requestRssFeed() {

        val editText = EditText(application)
        editText.setText(articlesViewModel.getUrl())

        MaterialAlertDialogBuilder(this)
            .setTitle("RSS Url")
            .setView(editText)
            .setNegativeButton("Cancel") { dialog, which -> dialog.dismiss() }
            .setPositiveButton("Ok") { dialog, whichButton ->
                articlesViewModel.setUrl(editText.text.toString())
                swipeLayout.setRefreshing(true)
                articlesViewModel.fetchFeed()
            }
            .show()

    }

    override fun onStop() {

        super.onStop()
        unregisterReceiver(networkReceiver)

    }

}

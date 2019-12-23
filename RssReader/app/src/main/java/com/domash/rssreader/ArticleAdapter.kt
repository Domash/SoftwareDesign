package com.domash.rssreader

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import androidx.recyclerview.widget.RecyclerView
import com.prof.rssparser.Article
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.article_item.view.*


class ArticleAdapter(val articles: MutableList<Article>) : RecyclerView.Adapter<ArticleAdapter.ArticleHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.article_item, parent, false)
        return ArticleHolder(view)
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    override fun onBindViewHolder(holder: ArticleHolder, position: Int) {
        holder.bind(articles[position])
    }

    inner class ArticleHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        internal fun bind(article: Article) {

            Picasso.get()
                   .load(article.image)
                   .placeholder(R.drawable.placeholder)
                   .into(itemView.image)

            itemView.title.text = article.title
            itemView.pubDate.text = article.pubDate.toString()
            itemView.categories.text = article.categories.joinToString(separator = ",")

            itemView.setOnClickListener {

                val articleWebView = WebView(itemView.context)

                articleWebView.settings.loadWithOverviewMode = true
                articleWebView.settings.javaScriptEnabled = true
                articleWebView.webChromeClient = WebChromeClient()
                //articleWebView.loadUrl(article.content)

                articleWebView.loadUrl("http://www.hkmytravel.com")



            }


        }

    }

}
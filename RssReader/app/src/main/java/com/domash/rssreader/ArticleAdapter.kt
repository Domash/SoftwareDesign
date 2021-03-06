package com.domash.rssreader

import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.widget.TextView
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
            itemView.pubDate.text = article.pubDate.toString().substring(0, article.pubDate.toString().length - 5)
            itemView.categories.text = article.categories.joinToString(separator = ", ")

            itemView.setOnClickListener {

                val articleView = WebView(itemView.context)
                articleView.settings.loadWithOverviewMode = true
                articleView.settings.javaScriptEnabled = true
                articleView.isHorizontalScrollBarEnabled = false
                articleView.webChromeClient = WebChromeClient()
                articleView.loadDataWithBaseURL(
                    null, "<style>img{display: inline; height: auto; max-width: 100%;} " +
                        "</style>\n" + "<style>iframe{ height: auto; width: auto;}" + "</style>\n" + article.content, null, "utf-8", null)

                val alertDialog = androidx.appcompat.app.AlertDialog.Builder(itemView.context).create()
                alertDialog.setTitle(article.title)
                alertDialog.setView(articleView)
                alertDialog.setButton(androidx.appcompat.app.AlertDialog.BUTTON_NEUTRAL, "OK") { dialog, _ ->
                    dialog.dismiss()
                }
                alertDialog.show()
                (alertDialog.findViewById<View>(android.R.id.message) as TextView).movementMethod = LinkMovementMethod.getInstance()
            }


        }

    }

}
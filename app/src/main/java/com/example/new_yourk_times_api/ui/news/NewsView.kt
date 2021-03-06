package com.example.new_yourk_times_api.ui.news

import com.example.new_yourk_times_api.ui.news.templates.VisualObject

interface NewsView {
    fun showLoader(isShow: Boolean)

    fun showMessage(message: String)

    fun updateNews(news: List<VisualObject>)
}
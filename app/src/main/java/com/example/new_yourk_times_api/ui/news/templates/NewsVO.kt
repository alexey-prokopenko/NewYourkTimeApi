package com.example.new_yourk_times_api.ui.news.templates



class NewsVO (
        val url: String,
        val snippet: String,
        val section: String,
        val pubDate: String
        ): VisualObject{
    override val id: String
        get() = url
}

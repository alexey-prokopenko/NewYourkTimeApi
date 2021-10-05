package com.example.new_yourk_times_api.ui.books.templates

import com.example.new_yourk_times_api.ui.news.templates.VisualObject

class BooksVO(
    val urlAmazon: String,
    val title: String,
    val description: String,
    val author: String
) : VisualObject {
    override val id: String
        get() = urlAmazon
}

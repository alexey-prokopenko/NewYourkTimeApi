package com.example.new_yourk_times_api.ui.books

import com.example.new_yourk_times_api.ui.news.templates.VisualObject

interface BooksView {
        fun showLoader(isShow: Boolean)

        fun showMessage(message: String)

        fun updateBooks(books: List<VisualObject>)
}
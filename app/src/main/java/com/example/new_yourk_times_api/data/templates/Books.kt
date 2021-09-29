package com.example.new_yourk_times_api.data.templates

class Books (
    val urlAmazon : String,
    val booksDetail : List<BookDetailsItem?>
            ) {

    val title: List<String> = booksDetail
        .map{ bookDetailsItem -> bookDetailsItem?.title.toString()}

    val description: List<String> = booksDetail
        .map{bookDetailsItem -> bookDetailsItem?.description.orEmpty() }

    val author: List<String> = booksDetail.
    map { bookDetailsItem -> bookDetailsItem?.author.orEmpty()}
    }



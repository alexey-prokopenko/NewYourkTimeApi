package com.example.new_yourk_times_api.data.domain.mappers

import com.example.new_yourk_times_api.data.domain.models.BooksDb
import com.example.new_yourk_times_api.data.templates.Books

class BooksMapper: Mapper<Books, BooksDb> {
    override fun map(model: Books): BooksDb =
        with(model){
            BooksDb(
                urlAmazon = urlAmazon,
                title =  title.toString() ,
                description = description.toString(),
                author = author.toString()
            )
        }
}
package com.example.new_yourk_times_api.ui.books.mappers

import com.example.new_yourk_times_api.data.domain.mappers.Mapper
import com.example.new_yourk_times_api.data.domain.models.BooksDb
import com.example.new_yourk_times_api.ui.books.templates.BooksVO

class BooksVoMapper: Mapper<BooksDb, BooksVO> {
    override fun map(model: BooksDb): BooksVO =
        with(model){
            BooksVO(
                urlAmazon = urlAmazon,
                title = title,
                description = description,
                author = author,
            )
        }
}
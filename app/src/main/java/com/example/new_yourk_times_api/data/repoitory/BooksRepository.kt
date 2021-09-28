package com.example.new_yourk_times_api.data.repoitory

import com.example.new_yourk_times_api.data.network.books.NyTimesApiBooks
import com.example.new_yourk_times_api.data.templates.ResponseBooks
import retrofit2.Call

interface BooksRepository {
    fun getBooks(): Call<ResponseBooks>
}

internal class BooksRepositoryImpl(private val api: NyTimesApiBooks) : BooksRepository {
    override fun getBooks(): Call<ResponseBooks> {
        return api.getInfoBooks("hardcover-fiction")
    }

}
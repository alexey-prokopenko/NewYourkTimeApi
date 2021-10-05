package com.example.new_yourk_times_api.data.repoitory

import com.example.new_yourk_times_api.application.executors.Executors
import com.example.new_yourk_times_api.data.database.NewsDatabase
import com.example.new_yourk_times_api.data.domain.ResultListener
import com.example.new_yourk_times_api.data.domain.mappers.Mapper
import com.example.new_yourk_times_api.data.domain.models.BooksDb
import com.example.new_yourk_times_api.data.network.books.NyTimesApiBooks
import com.example.new_yourk_times_api.data.templates.Books
import com.example.new_yourk_times_api.data.templates.ResponseBooks
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


interface BooksRepository {
    fun getBooks(
        forceUpdate: Boolean,
        resultListener: ResultListener<List<BooksDb>>
    )
}

internal class BooksRepositoryImpl(
    private val api: NyTimesApiBooks,
    private val booksMapper: Mapper<Books, BooksDb>,
    private val database: NewsDatabase,
    private val executors: Executors
) : BooksRepository {


    override fun getBooks(
        forceUpdate: Boolean,
        resultListener: ResultListener<List<BooksDb>>
    ) {
        if (forceUpdate) {
            getFromRemote(resultListener)
        } else {
            executors.background.execute {
                val dbBooks: List<BooksDb> = database.booksDao().getAll()

                if (dbBooks.isNotEmpty()) {
                    resultListener.onSuccess(dbBooks)
                } else {
                    getFromRemote (resultListener)
                }
            }
        }
    }

    private fun getFromRemote(resultListener: ResultListener<List<BooksDb>>) =
        api.getInfoBooks("hardcover-fiction")
            .enqueue(object : Callback<ResponseBooks> {
                override fun onResponse(
                    call: Call<ResponseBooks>,
                    response: Response<ResponseBooks>
                ) {
                    val results = response.body()?.results
                    if (response.isSuccessful && !results.isNullOrEmpty()) {
                        val books = results.map { resultsItem ->
                            Books(
                                urlAmazon = resultsItem?.amazonProductUrl.orEmpty(),
                                booksDetail = resultsItem?.bookDetails.orEmpty()
                            )
                        }.map(booksMapper::map)
                        resultListener.onSuccess(books)

                        executors.background.execute{database.booksDao().insertAll(books)}
                    }
                }

                override fun onFailure(call: Call<ResponseBooks>, t: Throwable) {
                    resultListener.onError(t)
                }

            })



}
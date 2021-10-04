package com.example.new_yourk_times_api.presenter

import com.example.new_yourk_times_api.data.repoitory.BooksRepository
import com.example.new_yourk_times_api.data.templates.Books
import com.example.new_yourk_times_api.data.templates.ResponseBooks
import com.example.new_yourk_times_api.ui.books.BooksView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BooksPresenter (private val booksRepository: BooksRepository){

    private var view: BooksView? = null

    fun attach(view: BooksView){
        this.view = view
    }

    fun detach() {
        view = null
    }
    fun  getListOfBooks() {
        booksRepository.getBooks().enqueue(object : Callback<ResponseBooks> {
            override fun onResponse(call: Call<ResponseBooks>, response: Response<ResponseBooks>) {
                val results = response.body()?.results
                if (response.isSuccessful && !results.isNullOrEmpty()) {
                    val books = results.map { resultsItem ->
                        Books(
                            urlAmazon = resultsItem?.amazonProductUrl.orEmpty(),
                            booksDetail = resultsItem?.bookDetails.orEmpty()
                        )
                    }
                    view?.updateBooks(books)

                }
            }

            override fun onFailure(call: Call<ResponseBooks>, t: Throwable) {
                view?.showMessage("WRONG")
            }

        })
    }

}
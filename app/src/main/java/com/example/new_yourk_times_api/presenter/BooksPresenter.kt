package com.example.new_yourk_times_api.presenter

import com.example.new_yourk_times_api.data.domain.ResultListener
import com.example.new_yourk_times_api.data.domain.mappers.Mapper
import com.example.new_yourk_times_api.data.domain.models.BooksDb
import com.example.new_yourk_times_api.data.repoitory.BooksRepository
import com.example.new_yourk_times_api.ui.books.BooksView
import com.example.new_yourk_times_api.ui.books.templates.BooksVO
import com.example.new_yourk_times_api.ui.news.templates.VisualObject

class BooksPresenter (private val booksRepository: BooksRepository,
    private val booksVoMapper: Mapper<BooksDb, BooksVO>
){

    private var view: BooksView? = null
    private var books: List<VisualObject> = emptyList()

    fun attach(view: BooksView){
        this.view = view
    }

    fun detach() {
        view = null
    }

    fun loadData(forceUpdate: Boolean){
        view?.showLoader(true)
        booksRepository.getBooks(
            forceUpdate = forceUpdate,
            resultListener = handleResult()
        )
    }

    private fun handleResult() = object : ResultListener<List<BooksDb>> {
        override fun onSuccess(data: List<BooksDb>) {
            val booksVO = data.map(booksVoMapper::map)

            val dataList: MutableList<VisualObject> = mapWithDates(booksVO)

            books = dataList

            view?.showLoader(false)
            view?.updateBooks(dataList)
        }

        override fun onError(error: Throwable) {
            view?.showLoader(false)
            view?.showMessage("Something is wrong")
        }
    }


    private fun mapWithDates(booksVO: List<BooksVO>): MutableList<VisualObject> {
        val dataList: MutableList<VisualObject> = mutableListOf()

        if (booksVO.isNotEmpty()) {

            booksVO.forEach { booksVO ->
                dataList.add(booksVO)
            }
        }
        return dataList
    }
   /* fun  getListOfBooks() {
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
    }*/

}
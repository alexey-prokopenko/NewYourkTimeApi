package com.example.new_yourk_times_api.presenter

import com.example.new_yourk_times_api.data.repoitory.NewsRepository
import com.example.new_yourk_times_api.data.templates.News
import com.example.new_yourk_times_api.data.templates.ResponseNews
import com.example.new_yourk_times_api.ui.news.NewsView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsPresenter (private val newsRepository: NewsRepository){

    private var view: NewsView? = null

    fun attach(view: NewsView){
        this.view = view
    }

    fun detach() {
        view = null
    }
    fun  getListOfNews() {
        newsRepository.getNews().enqueue(object : Callback<ResponseNews> {
                override fun onResponse(call: Call<ResponseNews>, response: Response<ResponseNews>) {
                    val results = response.body()?.response?.docs
                    if (response.isSuccessful && !results.isNullOrEmpty()) {
                        val news = results.map { resultsItem ->
                            News(
                                snippet = resultsItem?.snippet.orEmpty(),
                                url = resultsItem?.webUrl.orEmpty(),
                                section = resultsItem?.sectionName.orEmpty()
                            )
                        }
                        view?.updateNews(news)

                    }
                }

                override fun onFailure(call: Call<ResponseNews>, t: Throwable) {
                    view?.showMessage("WRONG")
                }

            })
        }

}


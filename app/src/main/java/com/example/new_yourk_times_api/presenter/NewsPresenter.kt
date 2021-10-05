package com.example.new_yourk_times_api.presenter

import com.example.new_yourk_times_api.data.domain.ResultListener
import com.example.new_yourk_times_api.data.domain.mappers.Mapper
import com.example.new_yourk_times_api.data.domain.models.NewsDb
import com.example.new_yourk_times_api.data.repoitory.NewsRepository
import com.example.new_yourk_times_api.ui.news.NewsView
import com.example.new_yourk_times_api.ui.news.templates.DateVO
import com.example.new_yourk_times_api.ui.news.templates.NewsVO
import com.example.new_yourk_times_api.ui.news.templates.VisualObject

class NewsPresenter (private val newsRepository: NewsRepository,
    private val newsVoMapper: Mapper<NewsDb, NewsVO>
){

    private var view: NewsView? = null

    private var news: List<VisualObject> = emptyList()

    fun attach(view: NewsView){
        this.view = view
    }

    fun detach() {
        view = null
    }

    fun loadData(forceUpdate: Boolean, section: String){
        view?.showLoader(true)
        newsRepository.getNews(
            forceUpdate = forceUpdate,
            section = section,
            resultListener = handleResult()
        )
    }

    fun handleQuery(query: CharSequence){
        var filteredList = news.filter{vo ->
            when (vo) {
                is NewsVO -> {
                    vo.snippet.contains(query, true) ||
                            vo.url.contains(query, true)

                }
                else -> true
            }
        }.filterIndexed{ index, vo ->
            when (vo) {
                is NewsVO -> true
                is DateVO -> {
                    if (index < news.size - 1) {
                        when (val next = news[index + 1]) {
                            is DateVO -> false
                            is NewsVO -> next.section == vo.name
                            else -> false
                        }
                    } else false
                }
                else -> false
                }
            }
        view?.updateNews(filteredList)

    }

    private fun handleResult() = object : ResultListener<List<NewsDb>> {
        override fun onSuccess(data: List<NewsDb>) {
            val newsVO = data.sortedBy { news -> news.section }
                .map(newsVoMapper::map)

            val dataList: MutableList<VisualObject> = mapWithDates(newsVO)

            news = dataList

            view?.showLoader(false)
            view?.updateNews(dataList)
        }

        override fun onError(error: Throwable) {
            view?.showLoader(false)
            view?.showMessage("Something is wrong")
        }
    }

    private fun mapWithDates(newsVO: List<NewsVO>): MutableList<VisualObject> {
        val dataList: MutableList<VisualObject> = mutableListOf()
        if (newsVO.isNotEmpty()) {
            var currentDate = DateVO(newsVO.first().pubDate)
            dataList.add(currentDate)

           newsVO.forEach { newsVO ->
                if (currentDate.name != newsVO.pubDate) {
                    currentDate = DateVO(newsVO.pubDate)
                    dataList.add(currentDate)
                }
                dataList.add(newsVO)
            }
        }
        return dataList
    }

}


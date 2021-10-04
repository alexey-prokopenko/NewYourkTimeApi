package com.example.new_yourk_times_api.data.repoitory


import com.example.new_yourk_times_api.application.executors.Executors
import com.example.new_yourk_times_api.data.database.NewsDatabase
import com.example.new_yourk_times_api.data.domain.ResultListener
import com.example.new_yourk_times_api.data.domain.mappers.Mapper
import com.example.new_yourk_times_api.data.domain.models.NewsDb
import com.example.new_yourk_times_api.data.network.news.NyTimesApi
import com.example.new_yourk_times_api.data.templates.DocsItem
import com.example.new_yourk_times_api.data.templates.ResponseNews
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


interface NewsRepository {
    fun getNews(
        forceUpdate: Boolean,
        section: String,
        resultListener: ResultListener<List<NewsDb>>)
}

internal class NewsRepositoryImpl(
    private val api: NyTimesApi,
    private val newsMapper: Mapper<DocsItem?, NewsDb>,
    private val database: NewsDatabase,
    private val executors: Executors
) : NewsRepository {

    override fun getNews(
        forceUpdate: Boolean,
        section: String,
        resultListener: ResultListener<List<NewsDb>>
    ) {
        if (forceUpdate) {
            getFromRemote(section, resultListener)
        } else {
            executors.background.execute {
                val dbNews: List<NewsDb> = database.newsDao().getAll()

                if (dbNews.isNotEmpty()) {
                    resultListener.onSuccess(dbNews)
                } else {
                    getFromRemote (section, resultListener)
                }
            }
        }
    }

    private fun getFromRemote( section: String, resultListener: ResultListener<List<NewsDb>>) =
        api.getProperties("news_desk:($section)")
            .enqueue(object : Callback<ResponseNews> {
                override fun onResponse(
                    call: Call<ResponseNews>,
                    response: Response<ResponseNews>
                ) {
                    val results = response.body()?.response?.docs
                    if (response.isSuccessful && !results.isNullOrEmpty()) {
                        val news = results.map(newsMapper::map)
                        resultListener.onSuccess(news)

                        executors.background.execute{database.newsDao().insertAll(news)}
                    }
                }

                override fun onFailure(call: Call<ResponseNews>, t: Throwable) {
                    resultListener.onError(t)
                }

            })

}
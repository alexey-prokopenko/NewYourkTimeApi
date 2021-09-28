package com.example.new_yourk_times_api.data.repoitory


import com.example.new_yourk_times_api.data.network.news.NyTimesApi
import com.example.new_yourk_times_api.data.templates.ResponseNews
import retrofit2.Call

interface NewsRepository {
    fun getNews(): Call<ResponseNews>
}

internal class NewsRepositoryImpl(private val api: NyTimesApi) : NewsRepository {
    override fun getNews(): Call<ResponseNews> {
        return api.getProperties()
    }

}
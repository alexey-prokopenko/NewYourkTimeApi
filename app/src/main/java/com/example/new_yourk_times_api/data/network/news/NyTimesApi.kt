package com.example.new_yourk_times_api.data.network.news


import com.example.new_yourk_times_api.data.templates.ResponseNews
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NyTimesApi {
    @GET("articlesearch.json")
    fun getProperties(
        @Query("fq") category: String,
    ): Call<ResponseNews>

}

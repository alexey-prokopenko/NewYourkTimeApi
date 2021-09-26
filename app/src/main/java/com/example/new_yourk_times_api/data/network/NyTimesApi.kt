package com.example.new_yourk_times_api.data.network


import com.example.new_yourk_times_api.data.templates.ResponseNews
import retrofit2.Call


import retrofit2.http.GET

interface NyTimesApi {
    @GET("articlesearch.json")
    fun getProperties(): Call<ResponseNews>
}

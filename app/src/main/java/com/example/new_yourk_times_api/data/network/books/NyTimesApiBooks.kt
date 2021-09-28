package com.example.new_yourk_times_api.data.network.books

import com.example.new_yourk_times_api.data.templates.ResponseBooks
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NyTimesApiBooks {
    @GET("lists.json")
    fun getInfoBooks (@Query("list") hardcover_fiction: String)
        : Call<ResponseBooks>
}
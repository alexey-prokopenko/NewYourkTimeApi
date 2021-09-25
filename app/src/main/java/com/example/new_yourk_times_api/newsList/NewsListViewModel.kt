package com.example.new_yourk_times_api.newsList


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.new_yourk_times_api.newsNetwork.Doc
import com.example.new_yourk_times_api.newsNetwork.NewsApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsListViewModel : ViewModel() {


    private val _response = MutableLiveData<String>()

    val response: LiveData<String>
        get() = _response

    init {
        getNewsProperties()
    }


    private fun getNewsProperties() {
        NewsApi.retrofitService.getProperties().enqueue(object : Callback<Doc> {
            override fun onFailure(call: Call<Doc>, t: Throwable) {
                _response.value = "Failure: " + t.message
            }

            override fun onResponse(call: Call<Doc>, response: Response<Doc>) {
                _response.value = "Success: ${response} News properties"
            }
        })
    }
}
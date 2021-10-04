package com.example.new_yourk_times_api.data.domain

interface ResultListener<T>{

    fun onSuccess(data: T)

    fun onError(error: Throwable)
}
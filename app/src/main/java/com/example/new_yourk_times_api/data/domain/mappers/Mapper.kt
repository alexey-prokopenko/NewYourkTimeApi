package com.example.new_yourk_times_api.data.domain.mappers

interface Mapper<E, K> {
    fun map(model: E): K
}
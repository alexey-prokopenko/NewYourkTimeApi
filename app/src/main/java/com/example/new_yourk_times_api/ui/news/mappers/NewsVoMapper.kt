package com.example.new_yourk_times_api.ui.news.mappers

import com.example.new_yourk_times_api.data.domain.mappers.Mapper
import com.example.new_yourk_times_api.data.domain.models.NewsDb
import com.example.new_yourk_times_api.ui.news.templates.NewsVO

class NewsVoMapper : Mapper<NewsDb, NewsVO> {
    override fun map(model: NewsDb): NewsVO =
        with(model) {
            NewsVO(
                url = url,
                snippet = snippet,
                section = section,
                pubDate = pubDate.substring(0, pubDate.length - 14)
            )
        }
}
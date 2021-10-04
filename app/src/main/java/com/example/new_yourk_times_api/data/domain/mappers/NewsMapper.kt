package com.example.new_yourk_times_api.data.domain.mappers

import com.example.new_yourk_times_api.data.domain.models.NewsDb
import com.example.new_yourk_times_api.data.templates.DocsItem

class NewsMapper: Mapper<DocsItem?, NewsDb> {
    override fun map(model: DocsItem?): NewsDb =
         with(model) {
            NewsDb(
                snippet = this!!.snippet.orEmpty(),
                url = webUrl.orEmpty(),
                section = sectionName.orEmpty(),
                pubDate = pubDate.orEmpty()
            )
        }

}
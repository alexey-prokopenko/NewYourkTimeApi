package com.example.new_yourk_times_api.newsNetwork

import com.squareup.moshi.Json




data class Doc (
    val abstract: String,

    @Json(name = "web_url")
    val webURL: String,

    val snippet: String,

    @Json(name = "lead_paragraph")
    val leadParagraph: String,


    @Json(name = "pub_date")
    val pubDate: String,

    @Json(name = "news_desk")
    val newsDesk: String,

    @Json(name = "section_name")
    val sectionName: String,

    @Json(name = "_id")
    val id: String,

    @Json(name = "word_count")
    val wordCount: Long,

    val uri: String,

    @Json(name = "subsection_name")
    val subsectionName: String? = null,

    @Json(name = "print_section")
    val printSection: String? = null,

    @Json(name = "print_page")
    val printPage: String? = null
)




package com.example.new_yourk_times_api.ui.news.templates

class DateVO(
    val name: String
) : VisualObject {
    override val id: String
        get() = name
}
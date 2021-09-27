package com.example.new_yourk_times_api.ui.news

import androidx.recyclerview.widget.DiffUtil
import com.example.new_yourk_times_api.data.templates.News

class NewsDiffUtils(private val oldList: List<News>, private val newList: List<News>) :
    DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return newList[newItemPosition].url == oldList[oldItemPosition].url
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return newList[newItemPosition] == oldList[oldItemPosition]
    }

}

package com.example.new_yourk_times_api.ui.books

import androidx.recyclerview.widget.DiffUtil
import com.example.new_yourk_times_api.data.templates.Books

class BooksDiffUtils (private val oldList: List<Books>, private val newList: List<Books>) :
    DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return newList[newItemPosition].urlAmazon == oldList[oldItemPosition].urlAmazon
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return newList[newItemPosition] == oldList[oldItemPosition]
    }

}
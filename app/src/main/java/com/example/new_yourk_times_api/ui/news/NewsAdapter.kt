package com.example.new_yourk_times_api.ui.news

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.new_yourk_times_api.databinding.ListItemDatesBinding
import com.example.new_yourk_times_api.databinding.ListItemNewsBinding
import com.example.new_yourk_times_api.ui.news.templates.DateVO
import com.example.new_yourk_times_api.ui.news.templates.NewsVO
import com.example.new_yourk_times_api.ui.news.templates.VisualObject


class NewsAdapter(private val clickListener: (NewsVO) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: List<VisualObject> = emptyList()

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is NewsVO -> VIEW_TYPE_NEWS
            is DateVO -> VIEW_TYPE_DATE
            else -> super.getItemViewType(position)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_NEWS -> {
                val binding = ListItemNewsBinding
                    .inflate(LayoutInflater.from(parent.context), parent, false)
                NewsViewHolder(binding, clickListener)
            }
            VIEW_TYPE_DATE -> {
                val binding = ListItemDatesBinding
                    .inflate(LayoutInflater.from(parent.context), parent, false)
                DateViewHolder(binding)
            }
            else -> throw IllegalArgumentException("Wrong View Type for this Adapter")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val item = items[position]) {
            is NewsVO -> (holder as NewsViewHolder).bind(item)
            is DateVO -> (holder as DateViewHolder).bind(item)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun update(items: List<VisualObject>) {
        if (this.items.isEmpty()) {
            this.items = items
            notifyDataSetChanged()
        } else {
            val callback = NewsDiffUtils(
                oldList = this.items,
                newList = items
            )

            this.items = items
            DiffUtil.calculateDiff(callback).dispatchUpdatesTo(this)
        }
    }

    class NewsViewHolder(
        private val binding: ListItemNewsBinding,
        private val clickListener: (NewsVO) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(news: NewsVO) {
            binding.root.setOnClickListener { clickListener(news) }
            binding.snippetField.text = news.snippet
            binding.section.text = news.section
        }
    }

    class DateViewHolder(
        private val binding: ListItemDatesBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(date: DateVO) {
            with(binding) {
                sectionDate.text = date.name
            }
        }
    }


    companion object {

        private const val VIEW_TYPE_NEWS = 1
        private const val VIEW_TYPE_DATE = 2
    }
}


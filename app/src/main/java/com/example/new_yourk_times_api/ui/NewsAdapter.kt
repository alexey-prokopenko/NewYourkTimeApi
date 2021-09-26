package com.example.new_yourk_times_api.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.new_yourk_times_api.data.templates.News
import com.example.new_yourk_times_api.databinding.ListItemNewsBinding


class NewsAdapter(private val clickListener: (News) -> Unit) :
    RecyclerView.Adapter<NewsAdapter.Viewholder>() {

    private var items: List<News> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        val binding =
            ListItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return Viewholder(binding, clickListener)
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun update(items: List<News>) {
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

    class Viewholder(
        private val binding: ListItemNewsBinding,
        private val clickListener: (News) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(news: News) {
            binding.root.setOnClickListener { clickListener(news) }
            binding.snippetField.text = news.snippet
            binding.urlField.text = news.url
        }
    }

}


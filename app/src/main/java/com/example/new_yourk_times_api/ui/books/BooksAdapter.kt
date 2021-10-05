package com.example.new_yourk_times_api.ui.books

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.new_yourk_times_api.databinding.ListItemBooksBinding
import com.example.new_yourk_times_api.ui.books.templates.BooksVO
import com.example.new_yourk_times_api.ui.news.templates.VisualObject

class BooksAdapter(private val clickListener: (BooksVO) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: List<VisualObject> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding =
            ListItemBooksBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BooksViewHolder(binding, clickListener)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val item = items[position]) {
            is BooksVO -> (holder as BooksViewHolder).bind(item)
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
            val callback = BooksDiffUtils(
                oldList = this.items,
                newList = items
            )

            this.items = items
            DiffUtil.calculateDiff(callback).dispatchUpdatesTo(this)
        }
    }

    class BooksViewHolder(
        private val binding: ListItemBooksBinding,
        private val clickListener: (BooksVO) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(books: BooksVO) {

            with(binding) {
                root.setOnClickListener { clickListener(books) }

                title.text = books.title.drop(1).dropLast(1)
                author.text = books.author.drop(1).dropLast(1)
                description.text = books.description.drop(1).dropLast(1)
            }
        }
    }

}
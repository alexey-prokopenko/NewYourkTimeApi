package com.example.new_yourk_times_api.ui.books

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.new_yourk_times_api.data.templates.Books
import com.example.new_yourk_times_api.databinding.ListItemBooksBinding

class BooksAdapter (private val clickListener: (Books) -> Unit) :
    RecyclerView.Adapter<BooksAdapter.Viewholder>() {

    private var items: List<Books> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        val binding =
            ListItemBooksBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return Viewholder(binding, clickListener)
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun update(items: List<Books>) {
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

    class Viewholder(
        private val binding: ListItemBooksBinding,
        private val clickListener: (Books) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(books: Books) {

            with(binding) {
                root.setOnClickListener { clickListener(books) }

                title.text = books.title.toString().drop(1).dropLast(1)
                author.text = books.author.toString().drop(1).dropLast(1)
                description.text = books.description.toString().drop(1).dropLast(1)
            }
        }
    }

}
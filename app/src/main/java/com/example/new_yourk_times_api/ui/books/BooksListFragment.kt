package com.example.new_yourk_times_api.ui.books

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.new_yourk_times_api.R
import com.example.new_yourk_times_api.data.network.books.BooksApiService
import com.example.new_yourk_times_api.data.network.books.NyTimesApiBooks
import com.example.new_yourk_times_api.data.repoitory.BooksRepository
import com.example.new_yourk_times_api.data.repoitory.BooksRepositoryImpl
import com.example.new_yourk_times_api.data.templates.Books
import com.example.new_yourk_times_api.databinding.FragmentBooksListBinding
import com.example.new_yourk_times_api.presenter.BooksPresenter
import com.google.android.material.snackbar.Snackbar


class BooksListFragment : Fragment(), BooksView {

    private val nyTimesApiBooks: NyTimesApiBooks by lazy { BooksApiService.nyTimesApiBooks }
    private val booksRepository: BooksRepository by lazy { BooksRepositoryImpl(nyTimesApiBooks) }
    private val presenter: BooksPresenter by lazy(LazyThreadSafetyMode.NONE) {
        BooksPresenter(booksRepository)
    }

    private val booksAdapter: BooksAdapter by lazy(LazyThreadSafetyMode.NONE) {
        BooksAdapter(::onBooksClick)
    }

    private lateinit var binding: FragmentBooksListBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBooksListBinding.inflate(inflater)

        presenter.attach(this)
        initRecycler()
        presenter.getListOfBooks()
        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.title_books_list)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detach()
    }

    override fun showLoader(isShow: Boolean) {

    }

    override fun showMessage(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).show()
    }

    override fun updateBooks(books: List<Books>) {
        booksAdapter.update(books)
    }

    private fun initRecycler() {
        with(binding.booksRecycler) {
            layoutManager = LinearLayoutManager(context)
            adapter = booksAdapter
        }
    }
    private fun onBooksClick(books: Books) {
        view?.findNavController()
            ?.navigate(
                BooksListFragmentDirections
                .actionBooksListFragmentToBooksDetailFragment(books.urlAmazon,
                    books.title.toString().drop(1).dropLast(1)
                ))
    }

}
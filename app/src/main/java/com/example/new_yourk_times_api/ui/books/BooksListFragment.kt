package com.example.new_yourk_times_api.ui.books

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.new_yourk_times_api.R
import com.example.new_yourk_times_api.application.executors.BackgroundExecutor
import com.example.new_yourk_times_api.application.executors.Executors
import com.example.new_yourk_times_api.application.executors.UiThreadExecutor
import com.example.new_yourk_times_api.data.database.DatabaseHolder
import com.example.new_yourk_times_api.data.database.NewsDatabase
import com.example.new_yourk_times_api.data.domain.mappers.BooksMapper
import com.example.new_yourk_times_api.data.domain.mappers.Mapper
import com.example.new_yourk_times_api.data.domain.models.BooksDb
import com.example.new_yourk_times_api.data.network.books.BooksApiService
import com.example.new_yourk_times_api.data.network.books.NyTimesApiBooks
import com.example.new_yourk_times_api.data.repoitory.BooksRepository
import com.example.new_yourk_times_api.data.repoitory.BooksRepositoryImpl
import com.example.new_yourk_times_api.data.templates.Books
import com.example.new_yourk_times_api.databinding.FragmentBooksListBinding
import com.example.new_yourk_times_api.presenter.BooksPresenter
import com.example.new_yourk_times_api.ui.books.mappers.BooksVoMapper
import com.example.new_yourk_times_api.ui.books.templates.BooksVO
import com.example.new_yourk_times_api.ui.news.templates.VisualObject
import com.google.android.material.snackbar.Snackbar


class BooksListFragment : Fragment(), BooksView {

    private val executors: Executors = Executors(
        mainThread = UiThreadExecutor(),
        background = BackgroundExecutor()
    )

    private val nyTimesApiBooks: NyTimesApiBooks by lazy { BooksApiService.nyTimesApiBooks }
    private val database: NewsDatabase by lazy { DatabaseHolder.newsDatabase }
    private val booksMapper: Mapper<Books, BooksDb> = BooksMapper()
    private val booksRepository: BooksRepository by lazy {
        BooksRepositoryImpl(nyTimesApiBooks, booksMapper, database, executors)
    }
    private val booksVoMapper: Mapper<BooksDb, BooksVO> = BooksVoMapper()
    private val presenter: BooksPresenter by lazy(LazyThreadSafetyMode.NONE) {
        BooksPresenter(booksRepository, booksVoMapper)
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
        initSwipeToRefresh()
        presenter.loadData(false)
        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title =
            getString(R.string.title_books_list)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detach()
    }

    override fun showLoader(isShow: Boolean) {
        binding.progressBar.isVisible = isShow
    }

    override fun showMessage(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).show()
    }

    override fun updateBooks(books: List<VisualObject>) {
        booksAdapter.update(books)
    }

    private fun initRecycler() {
        with(binding.booksRecycler) {
            layoutManager = LinearLayoutManager(context)
            adapter = booksAdapter
        }
    }

    private fun initSwipeToRefresh() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            presenter.loadData(true)
            binding.swipeRefreshLayout.isRefreshing = false
        }
    }

    private fun onBooksClick(books: BooksVO) {
        view?.findNavController()
            ?.navigate(
                BooksListFragmentDirections
                    .actionBooksListFragmentToBooksDetailFragment(
                        books.urlAmazon,
                        books.title.toString().drop(1).dropLast(1)
                    )
            )
    }

}
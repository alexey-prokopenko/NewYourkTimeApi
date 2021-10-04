package com.example.new_yourk_times_api.ui.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
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
import com.example.new_yourk_times_api.data.domain.mappers.Mapper
import com.example.new_yourk_times_api.data.domain.mappers.NewsMapper
import com.example.new_yourk_times_api.data.domain.models.NewsDb
import com.example.new_yourk_times_api.data.network.NewsSection
import com.example.new_yourk_times_api.data.network.news.NewsApiService
import com.example.new_yourk_times_api.data.network.news.NyTimesApi
import com.example.new_yourk_times_api.data.repoitory.NewsRepository
import com.example.new_yourk_times_api.data.repoitory.NewsRepositoryImpl
import com.example.new_yourk_times_api.data.templates.DocsItem
import com.example.new_yourk_times_api.databinding.FragmentNewsListBinding
import com.example.new_yourk_times_api.presenter.NewsPresenter
import com.example.new_yourk_times_api.ui.news.mappers.NewsVoMapper
import com.example.new_yourk_times_api.ui.news.templates.NewsVO
import com.example.new_yourk_times_api.ui.news.templates.VisualObject
import com.google.android.material.snackbar.Snackbar


class NewsListFragment : Fragment(), NewsView {

    private val executors: Executors = Executors(
        mainThread  = UiThreadExecutor(),
        background = BackgroundExecutor()
    )

    private val nyTimesApi: NyTimesApi by lazy { NewsApiService.nyTimesApi }
    private val database: NewsDatabase by lazy { DatabaseHolder.newsDatabase }
    private val newsMapper: Mapper<DocsItem?, NewsDb> = NewsMapper()
    private val newsRepository: NewsRepository by lazy {
        NewsRepositoryImpl(nyTimesApi, newsMapper,  database, executors) }
    private val newsVoMapper: Mapper<NewsDb, NewsVO> = NewsVoMapper()
    private val presenter: NewsPresenter by lazy(LazyThreadSafetyMode.NONE) {
        NewsPresenter(newsRepository, newsVoMapper)
    }

    private val newsAdapter: NewsAdapter by lazy(LazyThreadSafetyMode.NONE) {
        NewsAdapter(::onNewsClick)
    }

    private lateinit var binding: FragmentNewsListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewsListBinding.inflate(inflater)

        presenter.attach(this)
        initRecycler()
        initSearchView()
        initSwipeToRefresh()
        initSpinner()
        presenter.loadData(false, getCurrentCategory())
        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.title_News_list)
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


    override fun updateNews(news: List<VisualObject>) {
        newsAdapter.update(news)
    }


    private fun initRecycler() {
        with(binding.newsRecycler) {
            layoutManager = LinearLayoutManager(context)
            adapter = newsAdapter
        }
    }

    private fun initSearchView() {
        with(binding.searchView) {
            setOnClickListener { isIconified = false }
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    presenter.handleQuery(query.orEmpty())
                    return true
                }

                override fun onQueryTextChange(query: String?): Boolean {
                    presenter.handleQuery(query.orEmpty())
                    return true
                }
            })
        }
    }

    private fun initSwipeToRefresh() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            presenter.loadData(true, getCurrentCategory())
            binding.swipeRefreshLayout.isRefreshing = false
        }
    }

    private fun initSpinner() {
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.sections_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.sectionsSpinner.adapter = adapter
            binding.sectionsSpinner.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                        presenter.loadData(true, NewsSection.values()[position].section)
                    }

                    override fun onNothingSelected(p0: AdapterView<*>?) {

                    }
                }
        }
    }


    private fun onNewsClick(news: NewsVO) {
        view?.findNavController()
            ?.navigate(NewsListFragmentDirections
                .actionNewsListFragmentToNewsDetailFragment(news.url))
    }

    private fun getCurrentCategory() : String =
        NewsSection.values()[binding.sectionsSpinner.selectedItemPosition].section

}
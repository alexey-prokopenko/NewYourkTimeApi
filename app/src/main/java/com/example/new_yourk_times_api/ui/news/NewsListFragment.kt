package com.example.new_yourk_times_api.ui.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.new_yourk_times_api.data.network.NewsApiService
import com.example.new_yourk_times_api.data.network.NyTimesApi
import com.example.new_yourk_times_api.data.repoitory.NewsRepository
import com.example.new_yourk_times_api.data.repoitory.NewsRepositoryImpl
import com.example.new_yourk_times_api.data.templates.News
import com.example.new_yourk_times_api.databinding.FragmentNewsListBinding
import com.example.new_yourk_times_api.presenter.NewsPresenter
import com.google.android.material.snackbar.Snackbar


class NewsListFragment : Fragment(), NewsView {

    private val nyTimesApi: NyTimesApi by lazy { NewsApiService.nyTimesApi }
    private val newsRepository: NewsRepository by lazy { NewsRepositoryImpl(nyTimesApi) }
    private val presenter: NewsPresenter by lazy(LazyThreadSafetyMode.NONE) {
        NewsPresenter(newsRepository)
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
        presenter.getListOfNews()
        setHasOptionsMenu(true)

        return binding.root
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

    override fun updateNews(news: List<News>) {
        newsAdapter.update(news)
    }

    private fun initRecycler() {
        with(binding.newsRecycler) {
            layoutManager = LinearLayoutManager(context)
            adapter = newsAdapter
        }
    }

    private fun onNewsClick(news: News) {
        val newsUrl = news.url
        view?.findNavController()
            ?.navigate(NewsListFragmentDirections
                .actionNewsListFragmentToNewsDetailFragment(newsUrl))
    }

}
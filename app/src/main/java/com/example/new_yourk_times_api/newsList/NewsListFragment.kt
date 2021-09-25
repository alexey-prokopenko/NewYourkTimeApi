package com.example.new_yourk_times_api.newsList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.new_yourk_times_api.databinding.FragmentNewsListBinding


class NewsListFragment : Fragment() {

    private val viewModel:  NewsListViewModel by lazy {
        ViewModelProvider(this).get(NewsListViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = FragmentNewsListBinding.inflate(inflater)

        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        setHasOptionsMenu(true)
        return binding.root
    }




}
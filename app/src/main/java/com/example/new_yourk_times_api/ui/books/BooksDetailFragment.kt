package com.example.new_yourk_times_api.ui.books

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.new_yourk_times_api.databinding.FragmentBooksDetailBinding


class BooksDetailFragment : Fragment() {
    private lateinit var binding: FragmentBooksDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val args = BooksDetailFragmentArgs.fromBundle(requireArguments())

        binding = FragmentBooksDetailBinding.inflate(inflater)
        setUpWebView(args.booksUrl)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args = BooksDetailFragmentArgs.fromBundle(requireArguments())
        (activity as AppCompatActivity).supportActionBar?.title = args.booksTitle
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setUpWebView(url: String) {
        val wbWebview: WebView = binding.wbWebView
        wbWebview.loadUrl(url)

        val webSetting = wbWebview.settings
        webSetting.javaScriptEnabled = true

        wbWebview.webViewClient = WebViewClient()

        wbWebview.canGoBack()
        wbWebview.setOnKeyListener(View.OnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_BACK
                && event.action == MotionEvent.ACTION_UP
                && wbWebview.canGoBack()
            ) {
                wbWebview.goBack()
                return@OnKeyListener true
            }
            false
        })
    }

}
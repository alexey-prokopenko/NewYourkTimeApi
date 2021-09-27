package com.example.new_yourk_times_api.ui.news

import android.os.Bundle
import android.view.*
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.new_yourk_times_api.databinding.FragmentNewsDetailBinding


class NewsDetailFragment : Fragment() {


     private lateinit var binding: FragmentNewsDetailBinding

     override fun onCreateView(
          inflater: LayoutInflater,
          container: ViewGroup?,
          savedInstanceState: Bundle?
     ): View? {

          val args = NewsDetailFragmentArgs.fromBundle(requireArguments())
          Toast.makeText(context, "NumCorrect: ${args.newsUrl}", Toast.LENGTH_LONG).show()

          binding = FragmentNewsDetailBinding.inflate(inflater)
          setUpWebView(args.newsUrl)
          return binding.root
     }

     private fun setUpWebView (url: String) {
          val wbWebview : WebView = binding.wbWebView
          wbWebview.loadUrl(url)

          val webSetting = wbWebview.settings
          webSetting.javaScriptEnabled = true

          wbWebview.webViewClient = WebViewClient()

          wbWebview.canGoBack()
          wbWebview.setOnKeyListener(View.OnKeyListener{v, keyCode, event ->
               if (keyCode == KeyEvent.KEYCODE_BACK
                    && event.action == MotionEvent.ACTION_UP
                    && wbWebview.canGoBack()){
                    wbWebview.goBack()
                    return@OnKeyListener true
               }
                    false
          })
     }
}
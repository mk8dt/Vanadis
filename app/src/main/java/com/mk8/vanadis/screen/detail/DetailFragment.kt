package com.mk8.vanadis.screen.detail

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.mk8.vanadis.databinding.DetailLayoutBinding
import com.mk8.vanadis.screen.base.BaseFragment
import com.mk8.vanadis.screen.main.MainActivity

class DetailFragment : BaseFragment() {

    private lateinit var detailBinding: DetailLayoutBinding
    private lateinit var urlParam: String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        urlParam = arguments?.let { DetailFragmentArgs.fromBundle(it).url } ?: ""
        detailBinding = DetailLayoutBinding.inflate(layoutInflater)
        return detailBinding.root
    }

    override fun initView() {
        detailBinding.webViewDetail.apply {
            webViewClient = MyWebViewClient()
            loadUrl(urlParam)
        }
    }

    inner class MyWebViewClient : WebViewClient() {

        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
            val url: String = request?.url.toString()
            view?.loadUrl(url)
            return true
        }

        override fun shouldOverrideUrlLoading(webView: WebView, url: String): Boolean {
            webView.loadUrl(url)
            return true
        }

        override fun onReceivedError(view: WebView, request: WebResourceRequest, error: WebResourceError) {
            Toast.makeText(context, "$error", Toast.LENGTH_SHORT).show()
        }
    }
}
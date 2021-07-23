package kr.hs.dgsw.donghyeon.iframe_example.widget

import android.util.Base64
import android.util.Base64.encodeToString
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.databinding.BindingAdapter

object BindingAdapter {

    @JvmStatic
    @BindingAdapter("bind:web")
    fun initializeWeb(webView : WebView, url : String) {
        val webSettings = webView.settings

        webView.webViewClient = WebViewClient()
        webView.scrollBarStyle = WebView.SCROLLBARS_OUTSIDE_OVERLAY
        webView.isScrollbarFadingEnabled = true

        webSettings.useWideViewPort = true
        webSettings.loadWithOverviewMode = true
        webSettings.javaScriptEnabled = true

        webView.loadUrl(url)
    }

    @JvmStatic
    @BindingAdapter("bind:link")
    fun loadWeb(webView: WebView, url : String) {
        webView.loadUrl(url)
    }
}
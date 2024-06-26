package cn.sichuan.cd.zzsy.ui.detail

import android.os.Bundle
import android.webkit.WebResourceRequest
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import cn.sichuan.cd.zzsy.databinding.ActivityDetailBinding
import cn.sichuan.cd.zzsy.model.app.base.BaseActivity


class DetailActivity : BaseActivity<ActivityDetailBinding>() {


    override fun initView(savedInstanceState: Bundle?) {
        initWebView()
    }

    override fun initData() {
        intent.getStringExtra("url")?.let {
            mBinding.wvDetail.loadUrl(it)
        }
    }

    private fun initWebView() {
        mBinding.wvDetail.setInitialScale(100)
        mBinding.wvDetail.webViewClient = webViewClient
        val ws = mBinding.wvDetail.settings
        with(ws) {
            loadWithOverviewMode = false
            setSupportZoom(true)
            builtInZoomControls = true
            displayZoomControls = false
            cacheMode = WebSettings.LOAD_DEFAULT
            useWideViewPort = true
            blockNetworkImage = false
            domStorageEnabled = true
            textZoom = 100
        }
        ws.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
    }

    private val webViewClient = object : WebViewClient() {

        @Deprecated("Deprecated in Java")
        override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
            url?.let { view?.loadUrl(it) }
            return true
        }

        override fun shouldOverrideUrlLoading(
            view: WebView?,
            request: WebResourceRequest?
        ): Boolean {
            view?.loadUrl(request?.url.toString())
            return true
        }
    }

}

package org.kcriss.mywebview


import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.*
import androidx.compose.ui.viewinterop.AndroidView
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collect

@Composable
fun MyWebView(webViewState: WebViewState) {
  var webVew by remember {
    mutableStateOf<WebView?>(null)
  }
  //webview 或 state 变化的时候重新订阅
  LaunchedEffect(webVew, webViewState) {
    with(webViewState) {
      //订阅流
      webVew?.handleEvent()
    }
  }

  AndroidView(factory = { context ->
    WebView(context).apply {
      webChromeClient = object : WebChromeClient() {
        override fun onReceivedTitle(view: WebView?, title: String?) {
          super.onReceivedTitle(view, title)
          webViewState.pageTitle = title
        }
      }
      webViewClient = object : WebViewClient() {
        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
          super.onPageStarted(view, url, favicon)
          webViewState.pageTitle = null
        }
      }

      with(settings) {
        javaScriptEnabled = true
      }
    }.also {
      webVew = it
    }
  }) { view ->
    when (val content = webViewState.content) {
      is WebContent.Url -> {
        val url = content.url
        if (url.isNotEmpty() && url != view.url) {
          view.loadUrl(url)
        }
      }
      is WebContent.Data -> {
        view.loadDataWithBaseURL("", content.data, null, "utf-8", null)
      }
    }
  }
}


@SuppressLint("RememberReturnType")
@Composable
fun rememberWebViewState(coroutineScope: CoroutineScope = rememberCoroutineScope(), url: String) =
  remember(key1 = url) {
    WebViewState(coroutineScope, WebContent.Url(url))
  }

@SuppressLint("RememberReturnType")
@Composable
fun rememberWebViewState(
  coroutineScope: CoroutineScope = rememberCoroutineScope(),
  data: String,
  baseUrl: String? = null
) =
  remember(key1 = data, key2 = baseUrl) {
    WebViewState(coroutineScope, WebContent.Data(data = data, baseUrl = baseUrl))
  }












package org.kcriss.mywebview

import android.webkit.WebView
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WebViewState(private val coroutineScope: CoroutineScope, webContent: WebContent) {
  //网页内容：url 或者data（html）内容
  var content by mutableStateOf(webContent)
  var pageTitle: String? by mutableStateOf(null)
    internal set

  //事件类型
  enum class EventType {
    EVALUATE_JAVASCRIPT  //执行Js方法
  }

  //共享流
  private val events: MutableSharedFlow<Event> = MutableSharedFlow()

  suspend fun WebView.handleEvent() = withContext(Dispatchers.Main) {
    events.collect { event ->
      when (event.type) {
        EventType.EVALUATE_JAVASCRIPT -> {
          evaluateJavascript(event.args, event.callback)
        }
      }
    }
  }

  fun evaluateJavascript(script: String, resultCallback: ((String) -> Unit) = {}) {
    val event = Event(EventType.EVALUATE_JAVASCRIPT, script, resultCallback)
    coroutineScope.launch { events.emit(event) }
  }

}
package org.kcriss.mywebview


//共享流的数据类型
class Event(val type: WebViewState.EventType, val  args:String, val callback: ((String) -> Unit) = {})
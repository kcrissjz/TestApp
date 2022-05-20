package org.kcriss.mywebview

sealed class WebContent() {
  data class Url(val url: String) : WebContent()
  data class Data(val data: String, val baseUrl: String? = null) : WebContent()
}
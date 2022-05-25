package com.example.testapp.model.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ArticleEntity(
  val title: String,
  val source: String,
  @Json(name = "time")
  val timestamp:String,
  val tag:String?="1",
  var content: String? = ""
)

data class ArticleListResponse(val data: List<ArticleEntity>?):BaseResponse()
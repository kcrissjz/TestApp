package com.example.testapp.model.entity

data class Category(
  val title: String,
  val id: String
)

data class CategoryResponse(val data: List<Category>) : BaseResponse()
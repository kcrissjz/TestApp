package com.example.testapp.network.service

import com.example.testapp.model.entity.ArticleListResponse
import com.example.testapp.model.entity.CategoryResponse
import com.example.testapp.model.entity.SwiperResponse
import com.example.testapp.network.Network
import retrofit2.http.GET
import retrofit2.http.Query

interface ArticleService {
  companion object {
    fun instance(): ArticleService = Network.createService(ArticleService::class.java)
  }

  @GET("article/list?")
  suspend fun articleList(
    @Query("pageOffset") pageOffset: Int,
    @Query("pageSize") pageSize: Int
  ): ArticleListResponse
}
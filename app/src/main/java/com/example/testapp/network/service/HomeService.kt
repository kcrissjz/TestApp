package com.example.testapp.network.service

import com.example.testapp.model.entity.CategoryResponse
import com.example.testapp.model.entity.SwiperResponse
import com.example.testapp.network.Network
import retrofit2.http.GET

interface HomeService {
  companion object{
    fun instance():HomeService = Network.createService(HomeService::class.java)
  }
  @GET("category/list")
  suspend fun category():CategoryResponse
  @GET("recommand/banner")
  suspend fun banner(): SwiperResponse
}
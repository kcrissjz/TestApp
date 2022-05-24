package com.example.testapp.viewmodel

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.SmartDisplay
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import com.example.testapp.model.entity.Category
import com.example.testapp.model.entity.DataType
import com.example.testapp.model.entity.SwiperEntity
import com.example.testapp.network.service.HomeService

class MainViewmodel : ViewModel() {

  val homeService = HomeService.instance()

  //
  var categories by mutableStateOf(listOf(Category("","")))


  suspend fun categoryData() {
    val categoryRes = homeService.category()
    if (categoryRes.code == 0) {
      categories = categoryRes.data
    } else {
      val msg = categoryRes.message
    }
  }

  //分类下标
  var categoryIndex by mutableStateOf(0)
    private set

  fun updateCategoryIndex(index: Int) {
    categoryIndex = index
  }

  //
  val types by mutableStateOf(
    listOf(
      DataType("相关资讯", Icons.Default.Description),
      DataType("视频课程", Icons.Default.SmartDisplay),
    )
  )

  //分类下标
  var typeIndex by mutableStateOf(0)
    private set

  fun updateTypeIndex(index: Int) {
    typeIndex = index
  }

  //轮播图
  val swiperData = listOf(
    SwiperEntity("https://docs.bughub.icu/compose/assets/banner1.webp"),
    SwiperEntity("https://docs.bughub.icu/compose/assets/banner2.webp"),
    SwiperEntity("https://docs.bughub.icu/compose/assets/banner3.webp"),
    SwiperEntity("https://docs.bughub.icu/compose/assets/banner4.jpg"),
    SwiperEntity("https://docs.bughub.icu/compose/assets/banner5.jpg"),
  )

  val notificationData = listOf(
    "1dsfdsfad是大地发生的",
    "2fdjfgdnv你的伤口江南是假的",
    "3是大姐离开的少女的防护文化",
    "4阿萨德地方撒dsfjsdifoasdfcvx",
    "5姑父后空翻个会发光的表面v"
  )
}
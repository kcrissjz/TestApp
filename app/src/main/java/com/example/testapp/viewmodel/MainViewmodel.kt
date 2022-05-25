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

  var categoryLoaded by mutableStateOf(false)
    private set
  var swiperLoaded by mutableStateOf(false)
    private set
  //
  var categories by mutableStateOf(listOf(Category("","")))


  suspend fun categoryData() {
    val categoryRes = homeService.category()
    if (categoryRes.code == 0) {
      categories = categoryRes.data
      categoryLoaded = true
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
  var swiperData by mutableStateOf(listOf(SwiperEntity("")))
  suspend fun swiperData(){
    val swiperEntityRes = homeService.banner()
    if (swiperEntityRes.code == 0 && swiperEntityRes.data!=null){
      swiperData = swiperEntityRes.data
      swiperLoaded = true
    }else{
      val msg = swiperEntityRes.message
    }
  }

  val notificationData = listOf(
    "1dsfdsfad是大地发生的",
    "2fdjfgdnv你的伤口江南是假的",
    "3是大姐离开的少女的防护文化",
    "4阿萨德地方撒dsfjsdifoasdfcvx",
    "5姑父后空翻个会发光的表面v"
  )
}
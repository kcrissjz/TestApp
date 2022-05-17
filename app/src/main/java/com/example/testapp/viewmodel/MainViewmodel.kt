package com.example.testapp.viewmodel

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.SmartDisplay
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.testapp.model.entity.Category
import com.example.testapp.model.entity.DataType
import com.example.testapp.model.entity.SwiperEntity

class MainViewmodel : ViewModel() {

  //
  val categories by mutableStateOf(
    listOf(
      Category("思想政治"),
      Category("法律法规"),
      Category("职业道德"),
      Category("诚信自律")
    )
  )

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

}
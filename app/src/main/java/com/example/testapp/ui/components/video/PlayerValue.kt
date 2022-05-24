package com.example.testapp.ui.components.video

import android.os.Parcelable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.parcelize.Parcelize

/**
 * 比放弃相关数据类
 */
@Parcelize
class PlayerValue : Parcelable{
  var coverUrl:String = ""
  var title:String = ""
  //存储URL 目的是为了横竖屏切换等重绘的场景
  var videoUrl :String = ""
  //视频总时长
  var duration by mutableStateOf(0L)
  //当前播放进度
  var currentPosition by mutableStateOf(0L)

  //当前状态
  var state by mutableStateOf(PlayState.None)
}

/**
 * 播放状态
 */
enum class PlayState{
  None,
  Loading,
  Playing,
  Pause
}
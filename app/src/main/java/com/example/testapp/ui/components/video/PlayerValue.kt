package com.example.testapp.ui.components.video

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

/**
 * 比放弃相关数据类
 */
class PlayerValue {
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
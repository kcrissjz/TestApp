package com.example.testapp.ui.components.video


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Fullscreen
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.*


@Composable
fun VodeoPlayer(vodController: VodController) {

  var timeFormatText by remember {
    mutableStateOf("")
  }
  val MILLS_PER_SECOND = 1000
  val MILLS_PER_MINUTS = 60000
  val SECOND_PER_MINUTS = 60
  LaunchedEffect(vodController.playValue.currentPosition) {
    var positon = vodController.playValue.currentPosition
    val duration = vodController.playValue.duration

    //格式化时间
    timeFormatText = String.format(
      "%02d:%02d:%02d / %02d:%02d:%02d ",
      positon / MILLS_PER_MINUTS / SECOND_PER_MINUTS,
      positon / MILLS_PER_MINUTS,
      positon / MILLS_PER_SECOND % SECOND_PER_MINUTS,
      duration / MILLS_PER_MINUTS / SECOND_PER_MINUTS,
      duration / MILLS_PER_MINUTS,
      duration / MILLS_PER_SECOND % SECOND_PER_MINUTS,
    )
  }
  //适口显示控制层
  var showControlBar by remember {
    mutableStateOf(false)
  }
  var timer: Timer? = null

  Box(modifier = Modifier.clickable(
    interactionSource = remember {
      MutableInteractionSource()
    },
    indication = null
  ) {
    timer?.cancel()
    timer = null
    if (!showControlBar){
      timer = Timer()
      timer?.schedule(object :TimerTask(){
        override fun run() {
            showControlBar = false
        }
      },3000)
    }
    showControlBar = !showControlBar
  }
  ) {
    //视频播放区域
    VideoView(videoPlayer = vodController.vodPlayer)
    //视频控制层
    if (showControlBar)
      Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceBetween) {
        Spacer(modifier = Modifier.height(1.dp))

        Row(
          modifier = Modifier
            .fillMaxWidth()
            .height(45.dp)
            .background(
              Brush.verticalGradient(
                listOf(Color.Transparent, Color.Black),
              )
            ),
          verticalAlignment = Alignment.CenterVertically
        ) {
          //播放或暂停按钮
          IconButton(onClick = {
            if (vodController.playValue.state == PlayState.Playing) {
              vodController.pause()
            } else {
              vodController.resume()
            }
          }) {
            if (vodController.playValue.state == PlayState.Playing) {
              Icon(imageVector = Icons.Default.Pause, contentDescription = null, tint = Color.White)

            } else {
              Icon(
                imageVector = Icons.Default.PlayArrow,
                contentDescription = null,
                tint = Color.White
              )
            }
          }
          Spacer(modifier = Modifier.width(8.dp))
          //进度条
          Slider(
            value = vodController.playValue.currentPosition.toFloat(),
            onValueChange = {
              vodController.playValue.currentPosition = it.toLong()
              vodController.seekTo(it.toLong())
            },
            valueRange = 0f..vodController
              .playValue.duration.toFloat(),
            modifier = Modifier.weight(1f)
          )
          Spacer(modifier = Modifier.width(8.dp))
          //事件显示
          Text(text = timeFormatText, fontSize = 14.sp, color = Color.White)
          Spacer(modifier = Modifier.width(2.dp))
          //控制全屏
          IconButton(onClick = { /*TODO*/ }) {
            Icon(
              imageVector = Icons.Default.Fullscreen,
              contentDescription = null,
              tint = Color.White
            )
          }
          Spacer(modifier = Modifier.width(8.dp))

        }
      }
  }
}


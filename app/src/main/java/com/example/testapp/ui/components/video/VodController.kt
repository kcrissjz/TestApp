package com.example.testapp.ui.components.video

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.tencent.rtmp.ITXVodPlayListener
import com.tencent.rtmp.TXLiveConstants
import com.tencent.rtmp.TXLivePlayConfig
import com.tencent.rtmp.TXVodPlayer

class VodController(context: Context,val coverUrl:String,val videoUrl:String) {
  val playValue = PlayerValue()
  val vodPlayer = TXVodPlayer(context).apply {
    setVodListener(object : ITXVodPlayListener {
      override fun onPlayEvent(player: TXVodPlayer?, event: Int, param: Bundle?) {
        when (event) {
          TXLiveConstants.PLAY_EVT_PLAY_LOADING -> {
            playValue.state = PlayState.Loading
          }
          TXLiveConstants.PLAY_EVT_VOD_PLAY_PREPARED,
          TXLiveConstants.PLAY_EVT_RCV_FIRST_I_FRAME,
          TXLiveConstants.PLAY_EVT_PLAY_BEGIN,
          TXLiveConstants.PLAY_EVT_VOD_LOADING_END -> {
            playValue.state = PlayState.Playing
          }

          TXLiveConstants.PLAY_EVT_PLAY_PROGRESS -> {
            //获取视频时长进度
            playValue.duration = param?.getInt(TXLiveConstants.EVT_PLAY_DURATION_MS)?.toLong() ?: 0L
            playValue.currentPosition =
              param?.getInt(TXLiveConstants.EVT_PLAY_PROGRESS_MS)?.toLong() ?: 0L
          }
        }
      }

      override fun onNetStatus(player: TXVodPlayer?, param: Bundle?) {

      }

    })
  }


  fun startPlay(url: String) {
    vodPlayer.startPlay(url)
  }

  fun stopPlay() {
    vodPlayer.stopPlay(true)
  }

  fun pause() {
    vodPlayer.pause()
    playValue.state = PlayState.Pause
  }

  fun resume() {
    vodPlayer.resume()
  }

  fun seekTo(millseconds: Long) {
    vodPlayer.seek((millseconds / 1000).toInt())
  }

}

@SuppressLint("RememberReturnType")
@Composable
fun rememberVodController(coverUrl:String,videoUrl:String): VodController {
  val context = LocalContext.current
  return remember {
    VodController(context,coverUrl,videoUrl)
  }
}


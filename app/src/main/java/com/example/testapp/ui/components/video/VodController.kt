package com.example.testapp.ui.components.video

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.SaverScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.tencent.rtmp.ITXVodPlayListener
import com.tencent.rtmp.TXLiveConstants
import com.tencent.rtmp.TXLivePlayConfig
import com.tencent.rtmp.TXVodPlayer

class VodController(context: Context, val playValue: PlayerValue) {
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


  fun startPlay() {
    vodPlayer.startPlay(playValue.videoUrl)
  }

  /**
   * 配置改变后重新播放
   */
  fun restore() {
    vodPlayer.setStartTime(playValue.currentPosition / 1000f)
    startPlay()
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
fun rememberVodController(coverUrl: String, videoUrl: String, title: String): VodController {
  val context = LocalContext.current
  return rememberSaveable(saver = object : Saver<VodController, PlayerValue> {
    override fun restore(value: PlayerValue): VodController? {
      return VodController(context, value)
    }

    override fun SaverScope.save(value: VodController): PlayerValue {
      return value.playValue
    }

  }) {
    val playerValue = PlayerValue()
    playerValue.videoUrl = videoUrl
    playerValue.coverUrl = coverUrl
    playerValue.title = title
    return@rememberSaveable VodController(context, playerValue)
  }
  //  remember {
  //    VodController(context,coverUrl,videoUrl,title)
  //  }
}


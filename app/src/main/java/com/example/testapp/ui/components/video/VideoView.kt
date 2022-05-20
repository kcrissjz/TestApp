package com.example.testapp.ui.components.video


import android.view.LayoutInflater
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import com.example.testapp.R
import com.tencent.rtmp.TXVodPlayer
import com.tencent.rtmp.ui.TXCloudVideoView

@Composable
fun VideoView(videoPlayer: TXVodPlayer) {
  AndroidView(factory = { context ->
    (LayoutInflater.from(context).inflate(R.layout.video, null, false)
      .findViewById(R.id.videView) as TXCloudVideoView ).apply {
      videoPlayer.setPlayerView(this)
    }

  })
}



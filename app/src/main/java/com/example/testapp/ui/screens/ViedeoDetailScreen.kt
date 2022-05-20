package com.example.testapp.ui.screens


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.filled.ArrowLeft
import androidx.compose.material.icons.filled.TextFields
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.testapp.ui.components.TopAppBar
import com.example.testapp.ui.components.video.VideoView
import com.example.testapp.viewmodel.ArticleViewModel
import com.example.testapp.viewmodel.VideoViewModel
import com.tencent.rtmp.TXVodPlayer
import kotlinx.coroutines.launch
import org.kcriss.mywebview.MyWebView
import org.kcriss.mywebview.rememberWebViewState

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun VideoDetailScreen(onBack: () -> Unit = {}, vm: VideoViewModel = viewModel()) {
  val webViewState = rememberWebViewState(data = vm.videoDesc)
  val vodPlayer = TXVodPlayer(LocalContext.current)

  LaunchedEffect(vodPlayer ){
    vodPlayer.startPlay("http://vfx.mtime.cn/Video/2019/02/04/mp4/190204084208765161.mp4")
  }

  Column {
    TopAppBar() {
      ConstraintLayout(modifier = Modifier.fillMaxHeight()) {
        val (back, title, action) = createRefs()
        Icon(
          imageVector = Icons.Default.ArrowBackIos,
          contentDescription = null,
          tint = Color(0xffffffff),
          modifier = Modifier
            .size(19.dp)
            .constrainAs(back) {
              start.linkTo(parent.start, margin = 16.dp)
              centerVerticallyTo(parent)
            }
            .clickable { onBack() }
        )

        Text(
          text = "视频详情",
          color = Color.White,
          textAlign = TextAlign.Center,
          modifier = Modifier
            .fillMaxWidth()
            .constrainAs(title) {
              centerTo(parent)
            }
        )

      }


    }

    //视频区域
    Box(modifier = Modifier.height(200.dp)) {
      VideoView(videoPlayer = vodPlayer)
    }
    //简介
    MyWebView(webViewState = webViewState)
    
  }

}


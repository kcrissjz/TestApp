package com.example.testapp.ui.screens


import android.content.res.Configuration
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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.testapp.ui.components.TopAppBar
import com.example.testapp.ui.components.video.VideoView
import com.example.testapp.ui.components.video.VodeoPlayer
import com.example.testapp.ui.components.video.rememberVodController
import com.example.testapp.viewmodel.ArticleViewModel
import com.example.testapp.viewmodel.VideoViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.tencent.rtmp.TXVodPlayer
import kotlinx.coroutines.launch
import org.kcriss.mywebview.MyWebView
import org.kcriss.mywebview.rememberWebViewState

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun VideoDetailScreen(onBack: () -> Unit = {}, vm: VideoViewModel = viewModel()) {

  val systemUiController = rememberSystemUiController()
  val webViewState = rememberWebViewState(data = vm.videoDesc)
  val vodController = rememberVodController(coverUrl = vm.coverUrl, videoUrl = vm.videoUrl, title = vm.title)
  val configuration = LocalConfiguration.current
  var videoBoxModifier by remember {
    mutableStateOf(Modifier.aspectRatio(16 / 9f))
  }

  LaunchedEffect(configuration.orientation) {
    vodController.restore()
    if (configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
      videoBoxModifier = Modifier
        .fillMaxWidth()
        .aspectRatio(16 / 9f)
      systemUiController.isSystemBarsVisible = true
    } else {
      videoBoxModifier = Modifier
        .fillMaxSize()
      systemUiController.isSystemBarsVisible = false

    }
  }

  Column {
    if (configuration.orientation == Configuration.ORIENTATION_PORTRAIT)
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
    Box(modifier = videoBoxModifier) {
      VodeoPlayer(vodController)
    }
    //简介
    MyWebView(webViewState = webViewState)

  }

}


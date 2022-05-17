package com.example.testapp.ui.components


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.testapp.viewmodel.MainViewmodel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.VerticalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch
import java.util.*

@OptIn(ExperimentalPagerApi::class)
@Composable
fun NotificationContent(vm: MainViewmodel) {
  var initialIndex = Int.MAX_VALUE / 2

  vm.notificationData.forEachIndexed { index, s ->
    if (initialIndex % vm.notificationData.size != 0){
      initialIndex++
    }else{
      return@forEachIndexed
    }
  }
  val pagerState = rememberPagerState(initialPage = initialIndex)
  val coroutineScope = rememberCoroutineScope()
  DisposableEffect(key1 = Unit ){
    val timer = Timer()
    timer.schedule(object :TimerTask(){
      override fun run() {
        coroutineScope.launch {
          pagerState.animateScrollToPage(pagerState.currentPage+1)
        }
      }

    },2000,2000)
    onDispose {
      timer.cancel()
    }
  }

  Row(
    modifier = Modifier
      .padding(8.dp)
      .clip(RoundedCornerShape(8.dp))
      .background(Color(0x22149ee7))
      .height(45.dp),
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.SpaceBetween
  ) {
    Text(
      text = "最新活动",
      color = Color(0xff149ee7),
      fontSize = 14.sp,
      modifier = Modifier.padding(start = 8.dp)
    )
    Spacer(modifier = Modifier.width(8.dp))
    VerticalPager(
      count = Int.MAX_VALUE,
      state = pagerState,
      modifier = Modifier
        .weight(1f),
      horizontalAlignment = Alignment.Start
    ) { index ->
      Text(
        text = vm.notificationData[index % vm.notificationData.size], fontSize = 14.sp,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        color = Color(0xff333333),
      )
    }
    Spacer(modifier = Modifier.width(8.dp))
    Text(
      text = "更多", fontSize = 14.sp, color = Color(0xff666666),
      modifier = Modifier.padding(end = 8.dp)
    )
  }
}

@Preview
@Composable
fun NotificationContentPreview() {
  //  NotificationContent()
}


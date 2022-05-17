package com.example.testapp.ui.components


import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.testapp.viewmodel.MainViewmodel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch
import java.util.*

@OptIn(ExperimentalPagerApi::class)
@Composable
fun SwiperContent(vm:MainViewmodel) {
    val virtualCount = Int.MAX_VALUE
    val actualCount = vm.swiperData.size

    val initialIndex =  virtualCount / 2

    val pagerState = rememberPagerState(initialPage = initialIndex)
    val currentCoroutineScope = rememberCoroutineScope()
    DisposableEffect(key1 = Unit){
        val timer = Timer()
        timer.schedule(object :TimerTask(){
            override fun run() {
                currentCoroutineScope.launch {
                    pagerState.animateScrollToPage(pagerState.currentPage+1)
                }
            }

        },3000,3000)
        onDispose {
            timer.cancel()
        }
    }


    HorizontalPager(
        count = virtualCount,
        state = pagerState,
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .clip(RoundedCornerShape(8.dp))
    ) { index ->
        val actualIndex =  index % actualCount
        AsyncImage(
            model = vm.swiperData[actualIndex].imageUrl, contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(7 / 3f),
            contentScale = ContentScale.Crop
        )
    }
}

@Preview
@Composable
fun SwiperContentPreview() {
//  SwiperContent()
}


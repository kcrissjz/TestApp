package com.example.testapp.ui.screens


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.testapp.ui.components.*
import com.example.testapp.ui.components.TopAppBar
import com.example.testapp.viewmodel.ArticleViewModel
import com.example.testapp.viewmodel.MainViewmodel
import com.example.testapp.viewmodel.VideoViewModel
import com.google.accompanist.placeholder.placeholder
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import icu.bughub.app.app.extension.OnBottomReached
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun StudyScreen(
  vm: MainViewmodel = viewModel(),
  articleViewModel: ArticleViewModel = viewModel(),
  videoViewModel: VideoViewModel = viewModel(),
  onNavigateToArticle: () -> Unit = {},
  onNavigateToVideo: () -> Unit = {},
  onNavigateToHistory: () -> Unit = {},
  onLogout: () -> Unit = {},
) {
  val coroutineScope = rememberCoroutineScope()
  LaunchedEffect(Unit) {
    vm.categoryData()
    articleViewModel.fetchArticleList()
  }
  val lazyListState = rememberLazyListState()
  lazyListState.OnBottomReached(buffer = 3) {
    coroutineScope.launch { articleViewModel.loadMore()  }
  }
  Column(modifier = Modifier) {
    //?????????
    TopAppBar(modifier = Modifier.padding(horizontal = 8.dp)) {
      Surface(
        modifier = Modifier
          .clip(RoundedCornerShape(20.dp))
          .weight(1f),
        color = Color(0x33ffffff)
      ) {
        Row(
          modifier = Modifier.padding(
            horizontal = 8.dp,
            vertical = 4.dp
          ),
          verticalAlignment = Alignment.CenterVertically
        ) {
          Icon(
            imageVector = Icons.Default.Search, contentDescription = null,
            tint = Color.White,
            modifier = Modifier.size(14.dp)
          )
          Text(
            text = "?????????????????????????????????",
            color = Color.White, fontSize = 12.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
          )
        }
      }
      Spacer(modifier = Modifier.width(8.dp))

      Text(text = "??????\n??????", fontSize = 10.sp, color = Color.White, modifier = Modifier.clickable {
        onNavigateToHistory()
      })
      Spacer(modifier = Modifier.width(8.dp))

      Text(text = "26%", fontSize = 12.sp, color = Color.White, modifier = Modifier.clickable {
        onLogout()
      })
      Spacer(modifier = Modifier.width(8.dp))

      Icon(
        imageVector = Icons.Default.Notifications,
        contentDescription = null,
        tint = Color.White,
      )
    }

    TabRow(
      selectedTabIndex = vm.categoryIndex,
      backgroundColor = Color(0x22149ee7),
      contentColor = Color(0xff149ee7),
    ) {
      vm.categories.forEachIndexed { index, category ->
        Tab(
          selected = vm.categoryIndex == index,
          onClick = {
            vm.updateCategoryIndex(index)
          },
          selectedContentColor = Color(0xff149ee7),
          unselectedContentColor = Color(0xff666666),

          ) {
          Text(
            text = category.title,
            modifier = Modifier
              .padding(vertical = 8.dp)
              .placeholder(visible = !vm.categoryLoaded, color = Color.LightGray),
            fontSize = 14.sp
          )
        }
      }
    }


    TabRow(
      selectedTabIndex = vm.currentTypeIndex,
      backgroundColor = Color.Transparent,
      contentColor = Color(0xff149ee7),
      indicator = {},
      divider = {}
    ) {
      vm.types.forEachIndexed { index, type ->
        LeadingIconTab(
          selected = vm.currentTypeIndex == index,
          onClick = {
            vm.updateTypeIndex(index)
          },
          selectedContentColor = Color(0xff149ee7),
          unselectedContentColor = Color(0xff666666),
          text = {
            Text(
              text = type.title,
              modifier = Modifier
                .padding(vertical = 8.dp),
              fontSize = 16.sp
            )
          },
          icon = {
            Icon(imageVector = type.icon, contentDescription = null)
          }
        )
      }
    }

    SwipeRefresh(
      state = rememberSwipeRefreshState(isRefreshing = articleViewModel.refreshing),
      onRefresh = { coroutineScope.launch {  articleViewModel.refresh()  } }
    ) {
      LazyColumn(state = lazyListState) {
        item {
          SwiperContent(vm)
        }
        item {
          NotificationContent(vm = vm)
        }
        if (vm.currentTypeIndex == 0) {
          items(articleViewModel.list) { article ->
            ArticleItem(loaded = articleViewModel.listLoaded, article = article, modifier = Modifier.clickable {
              onNavigateToArticle()
            })
          }
        } else {
          items(videoViewModel.list) { video ->
            VideoItem(video, modifier = Modifier.clickable {
              onNavigateToVideo()
            })
          }
        }
      }
    }

  }
}


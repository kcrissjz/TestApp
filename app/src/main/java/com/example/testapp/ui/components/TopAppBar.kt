package com.example.testapp.ui.components


import android.view.WindowInsets
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.testapp.ui.theme.Blue200
import com.example.testapp.ui.theme.Blue700
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun TopAppBar(modifier: Modifier = Modifier,content: @Composable () -> Unit) {
  val systemUiController = rememberSystemUiController()
  LaunchedEffect(key1 = UInt ){
    systemUiController.setStatusBarColor(Color.Transparent)
  }

  val AppBarHeight = 56.dp
  //px转dp
  val statusBarHeightDp = with(LocalDensity.current){
    30.dp
  }
  Row(
    modifier = Modifier
      .fillMaxWidth()
      .background(
        Brush.linearGradient(
          listOf(Blue700, Blue200)
        )
      )
      .height(AppBarHeight + statusBarHeightDp)
      .padding(top = statusBarHeightDp)
      .then(modifier),
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.Center

  ) {
    content.invoke()
  }

}


@Preview
@Composable
fun TopAppBarPreview() {
//  TopAppBar() {
//    Text(text = "biaoti")
//  }
}


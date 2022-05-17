package com.example.testapp

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import com.example.testapp.ui.screens.MainFrame
import com.example.testapp.ui.theme.TestAppTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    //处理不同机型 状态栏不透明问题。导航栏遮盖内容问题------------------
//    window.statusBarColor = Color.Transparent.value.toInt()
//    window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
    //-------------------------------------------------------------------------------
    //让内容显示在状态栏和系统导航栏后面：状态栏和导航栏会遮盖部分内容
    WindowCompat.setDecorFitsSystemWindows(window,false)


    //获取状态栏高度
//    var statusBarHeight = 0
//    var resourceId = resources.getIdentifier("status_bar_height","dimen","android")
//    if (resourceId>0){
//      statusBarHeight = resources.getDimensionPixelSize(resourceId)
//    }

    setContent {
      TestAppTheme {
        // A surface container using the 'background' color from the theme
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
//          Greeting("Android")
          MainFrame()
        }
      }
    }
  }
}

@Composable
fun Greeting(name: String) {
  Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
  TestAppTheme {
    Greeting("Android")
  }
}
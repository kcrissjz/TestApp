package com.example.testapp.ui.screens


import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.testapp.ui.components.TopAppBar

@Composable
fun MineScreen() {
  Column(modifier = Modifier) {
    TopAppBar() {
      Text(text = "我的页面")
    }
    Text(text = "我的")
  }

}

@Preview
@Composable
fun MineScreenPreview() {
  MineScreen()
}


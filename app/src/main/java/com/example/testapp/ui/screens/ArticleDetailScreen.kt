package com.example.testapp.ui.screens


import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.testapp.ui.components.TopAppBar

@Composable
fun ArticleDetailScreen() {
  Column(modifier = Modifier) {
    TopAppBar() {
      Text(text = "文章详情", color = Color.White)
    }
  }
}

@Preview
@Composable
fun ArticleDetailScreenPreview() {
  ArticleDetailScreen()
}


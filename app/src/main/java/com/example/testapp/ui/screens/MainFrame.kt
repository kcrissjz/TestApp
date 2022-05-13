package com.example.testapp.ui.screens


import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.testapp.model.entity.NavigationItem

@Composable
fun MainFrame() {
  val navigationItems = listOf(
    NavigationItem("学习", icon = Icons.Filled.Home),
    NavigationItem("任务", icon = Icons.Default.DateRange),
    NavigationItem("我的", icon = Icons.Default.Person)
  )
  var currentNavigationIndex by remember {
    mutableStateOf(0)
  }
  Scaffold(bottomBar = {
    BottomNavigation(backgroundColor = MaterialTheme.colors.surface) {
      navigationItems.forEachIndexed { index, navigationItem ->
        BottomNavigationItem(
          selected = currentNavigationIndex == index,
          onClick = {
            currentNavigationIndex = index
          },
          icon = {
            Icon(imageVector = navigationItem.icon, contentDescription = null)
          },
          label = {
            Text(text = navigationItem.title)
          },
          selectedContentColor = Color(0xff149EE7),
          unselectedContentColor = Color(0xff999999)
        )
      }
    }
  }) {
    Text(text = "current item :${currentNavigationIndex}")
  }
}

@Preview
@Composable
fun MainFramePreview() {
  MainFrame()
}


package com.example.testapp.ui.components


import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import com.example.testapp.ui.navigation.Destinations
import com.example.testapp.ui.screens.ArticleDetailScreen
import com.example.testapp.ui.screens.MainFrame
import com.example.testapp.ui.screens.TaskScreen
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NavHostApp() {
  val navController = rememberAnimatedNavController()

  AnimatedNavHost(
    navController = navController,
    startDestination = Destinations.HomeFrame.route
  ) {
    composable(
      Destinations.HomeFrame.route,
      enterTransition = {
        slideIntoContainer(AnimatedContentScope.SlideDirection.Right)
      },
      exitTransition = {
        slideOutOfContainer(AnimatedContentScope.SlideDirection.Left)
      }
    ) {
      MainFrame(onNavigateToArticle = {
        navController.navigate(Destinations.ArticleDetail.route)
      })
    }
  }
}



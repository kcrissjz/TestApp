package com.example.testapp.ui.components


import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.example.testapp.compositionLocal.LocalUserViewModel
import com.example.testapp.ui.navigation.Destinations
import com.example.testapp.ui.screens.ArticleDetailScreen
import com.example.testapp.ui.screens.LoginScreen
import com.example.testapp.ui.screens.MainFrame
import com.example.testapp.ui.screens.VideoDetailScreen
import com.example.testapp.viewmodel.UserViewModel
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NavHostApp() {
  val navController = rememberAnimatedNavController()
//  ProvideWindowInsets() {
    CompositionLocalProvider(LocalUserViewModel provides UserViewModel()) {
      val userViewModel = LocalUserViewModel.current
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
          MainFrame(
            onNavigateToArticle = {
              navController.navigate(Destinations.ArticleDetail.route)
            },
            onNavigateToVideo = {
              navController.navigate(Destinations.VideoDetail.route)
            },
            onNavigateToHistory = {
              if (userViewModel.logged){

              }else{
                navController.navigate(Destinations.Login.route)
              }
            }
          )
        }
        composable(Destinations.ArticleDetail.route) {
          ArticleDetailScreen(onBack = {
            navController.popBackStack()
          })
        }
        composable(Destinations.VideoDetail.route) {
          VideoDetailScreen(onBack = {
            navController.popBackStack()
          })
        }
        composable(Destinations.Login.route) {
          LoginScreen(onBack = {
            navController.popBackStack()
          })
        }
      }
//    }
  }

}



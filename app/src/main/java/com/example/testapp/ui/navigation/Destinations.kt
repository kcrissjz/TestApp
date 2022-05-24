package com.example.testapp.ui.navigation

sealed class Destinations(val route:String){
  //首页大框架
  object HomeFrame:Destinations("HomeFrame")
  //文章详情页面
  object ArticleDetail:Destinations("ArticleDetail")
  //视频详情页面
  object VideoDetail:Destinations("VideoDetail")
  object Login:Destinations("Login")

}

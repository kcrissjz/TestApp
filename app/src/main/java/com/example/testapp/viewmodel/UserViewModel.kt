package com.example.testapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.testapp.model.entity.UserInfoEntity

class UserViewModel : ViewModel() {

  var userInfo: UserInfoEntity? = null
    private set

  //是否已经登录
  val logged: Boolean
    get() {
      return userInfo!=null
    }

  fun login(username :String,onBack:()->Unit){
    userInfo = UserInfoEntity(username)
    onBack.invoke()
  }
}
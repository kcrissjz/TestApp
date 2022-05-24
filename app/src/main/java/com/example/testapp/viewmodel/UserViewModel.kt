package com.example.testapp.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testapp.model.UserInfoManager
import com.example.testapp.model.entity.UserInfoEntity
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class UserViewModel(context: Context) : ViewModel() {
  private val userInfoManager = UserInfoManager(context)
  var userInfo: UserInfoEntity? = null
    private set

  init {
    viewModelScope.launch {
      val userName = userInfoManager.userName.firstOrNull()
      userInfo = if (userName?.isNotEmpty() == true){
        UserInfoEntity(userName = userName)
      }else{
        null
      }
    }
  }

  //是否已经登录
  val logged: Boolean
    get() {
      return userInfo!=null
    }

  fun login(username :String,onBack:()->Unit){
    userInfo = UserInfoEntity(username)
    viewModelScope.launch {
      userInfoManager.save(username)
      onBack()
    }
  }
}
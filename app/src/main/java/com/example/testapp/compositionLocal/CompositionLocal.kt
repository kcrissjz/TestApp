package com.example.testapp.compositionLocal

import androidx.compose.runtime.CompositionLocal
import androidx.compose.runtime.compositionLocalOf
import com.example.testapp.model.entity.UserInfoEntity
import com.example.testapp.viewmodel.UserViewModel

val LocalUserViewModel = compositionLocalOf<UserViewModel> { error("user view model context not found~") }

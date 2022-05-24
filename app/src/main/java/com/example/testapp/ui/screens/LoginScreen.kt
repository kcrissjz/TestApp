package com.example.testapp.ui.screens


import android.graphics.drawable.PaintDrawable
import android.view.animation.PathInterpolator
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.testapp.R
import com.example.testapp.compositionLocal.LocalUserViewModel
import com.example.testapp.ui.theme.Blue700
import com.example.testapp.viewmodel.UserViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun LoginScreen(onBack: () -> Unit = {}) {
  val userViewModel = LocalUserViewModel.current
  var screenWith: Float
  var screenHeight: Float
  var userName by remember {
    mutableStateOf("")
  }
  var passWord by remember {
    mutableStateOf("")
  }

  with(LocalDensity.current) {
    screenWith = LocalConfiguration.current.screenWidthDp.dp.toPx()
    screenHeight = LocalConfiguration.current.screenHeightDp.dp.toPx()
  }

  var showPassWord by remember {
    mutableStateOf(false)
  }

  Box(modifier = Modifier.fillMaxSize()) {
    Image(
      painter = painterResource(R.drawable.bg),
      contentDescription = null,
      contentScale = ContentScale.Crop,
      modifier = Modifier.fillMaxSize()
    )
    //右上往左下渐变层
    Box(
      modifier = Modifier
        .fillMaxSize()
        .background(
          Brush.linearGradient(
            listOf(Color(0xffbb8378), Color.Transparent),
            start = Offset(x = screenWith, y = 0f),
            end = Offset(x = 0f, y = screenHeight)
          )
        )
    )
    Box(
      modifier = Modifier
        .fillMaxSize()
        .background(
          Brush.linearGradient(
            listOf(Blue700, Color.Transparent),
            start = Offset(x = 0f, y = screenHeight),
            end = Offset(x = screenWith, y = 0f)
          )
        )
    )
    Icon(
      imageVector = Icons.Default.Close,
      contentDescription = null,
      tint = Color.White,
      modifier = Modifier
        .padding(horizontal = 16.dp, vertical = 32.dp)
        .clickable { onBack() })
    Column(
      modifier = Modifier.fillMaxSize(),
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.SpaceBetween
    ) {
      Text(
        text = "用户登录", fontSize = 20.sp, color = Color.White, fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(top = 100.dp)
      )
      Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
      ) {
        TextField(
          value = userName,
          onValueChange = { userName = it },
          singleLine = true,
          leadingIcon = {
            Icon(imageVector = Icons.Default.Person, contentDescription = null, tint = Color.White)
          },
          placeholder = {
            Text(text = "请输入用户名", color = Color.LightGray, fontSize = 14.sp)
          },
          colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent,
            focusedIndicatorColor = Color.LightGray,
            unfocusedIndicatorColor = Color.LightGray,
            focusedLabelColor = Color.LightGray,
            unfocusedLabelColor = Color.LightGray,
            cursorColor = Color.White
          )
        )
        TextField(
          value = passWord,
          onValueChange = { passWord = it },
          singleLine = true,
          leadingIcon = {
            Icon(
              imageVector = Icons.Default.LockOpen,
              contentDescription = null,
              tint = Color.White
            )
          },
          placeholder = {
            Text(text = "请输入密码", color = Color.LightGray, fontSize = 14.sp)
          },
          visualTransformation = if (showPassWord) VisualTransformation.None else PasswordVisualTransformation(),
          trailingIcon = {
            Icon(
              imageVector = if (showPassWord) Icons.Default.Visibility else Icons.Default.VisibilityOff,
              contentDescription = null,
              modifier = Modifier.clickable { showPassWord = !showPassWord },
              tint = Color.White
            )
          },
          colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent,
            focusedIndicatorColor = Color.LightGray,
            unfocusedIndicatorColor = Color.LightGray,
            focusedLabelColor = Color.LightGray,
            unfocusedLabelColor = Color.LightGray,
            cursorColor = Color.White
          )
        )
        TextButton(onClick = {
          userViewModel.login(userName, onBack = onBack)
        }) {
          Text(text = "登录", color = Color.White)
        }
      }
      Text(
        text = "还没有账号？点击立即注册",
        color = Color.Gray,
        fontSize = 14.sp,
        modifier = Modifier.padding(bottom = 30.dp)
      )
    }
  }

}

@Preview
@Composable
fun LoginScreenPreview() {
  LoginScreen()
}


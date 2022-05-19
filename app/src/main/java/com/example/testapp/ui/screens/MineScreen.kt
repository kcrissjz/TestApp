package com.example.testapp.ui.screens


import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.ArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.testapp.R
import com.example.testapp.ui.components.TopAppBar

@Composable
fun MineScreen() {
  val menus = listOf(
    MenuItem(R.drawable.points, "学习积分"),
    MenuItem(R.drawable.browser_history, "浏览记录"),
    MenuItem(R.drawable.archives, "学习档案"),
    MenuItem(R.drawable.questions, "常见问题"),
    MenuItem(R.drawable.version, "版本信息"),
    MenuItem(R.drawable.settings, "个人设置"),
    MenuItem(R.drawable.points, "联系我们"),
  )

  Column(modifier = Modifier) {
    TopAppBar() {
      Text(text = "我的页面", color = Color.White)
    }
    LazyColumn() {
      //头像
      item {
        ConstraintLayout {
          val (img, name, desc) = createRefs()
          Image(
            painter = painterResource(id = R.drawable.newbanner3), contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
              .clip(CircleShape)
              .size(60.dp)
              .border(width = 1.dp, color = Color.Blue)
              .constrainAs(img) {
                start.linkTo(parent.start, margin = 16.dp)
                top.linkTo(parent.top, margin = 32.dp)
              }
          )
          Text(text = "未登录", color = Color(0xff333333), fontSize = 18.sp,
            modifier = Modifier.constrainAs(name) {
              start.linkTo(img.end, margin = 8.dp)
              top.linkTo(img.top)
            })
          Text(text = "已坚持学习23天", color = Color(0xff999999), fontSize = 12.sp,
            modifier = Modifier.constrainAs(desc) {
              start.linkTo(img.end, margin = 8.dp)
              bottom.linkTo(img.bottom, margin = 4.dp)
            })
        }
      }
      //菜单
      itemsIndexed(menus) { index, menu ->
        if (index == 3) {
          Box(
            modifier = Modifier
              .background(color = Color(0xfff5f5f5))
              .height(16.dp)
              .fillMaxWidth()
          )
        }
        ConstraintLayout(modifier = Modifier.padding(vertical = 4.dp)) {
          val (icon, title, div, arrow) = createRefs()
          Icon(
            painter = painterResource(id = menu.icon),
            contentDescription = null,
            tint = Color(0xff149ee7),
            modifier = Modifier
              .size(17.dp)
              .constrainAs(icon) {
                start.linkTo(parent.start, margin = 8.dp)
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
              }
          )
          Text(
            text = menu.title,
            fontSize = 16.sp,
            color = Color(0xff333333),
            modifier = Modifier.constrainAs(title) {
              start.linkTo(icon.end, margin = 8.dp)
              top.linkTo(parent.top)
              bottom.linkTo(parent.bottom)
            })
          Divider(modifier = Modifier
            .constrainAs(div) {
              start.linkTo(title.start)
              top.linkTo(title.bottom, margin = 8.dp)
            }
          )

          Icon(
            imageVector = Icons.Default.ArrowForwardIos,
            contentDescription = null,
            tint = Color.Gray,
            modifier = Modifier
              .size(13.dp)
              .constrainAs(arrow) {
                end.linkTo(parent.end, margin = 8.dp)
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
              }
          )
        }
      }
    }
  }

}

data class MenuItem(@DrawableRes val icon: Int, val title: String)

@Preview
@Composable
fun MineScreenPreview() {
  MineScreen()
}


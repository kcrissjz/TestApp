package com.example.testapp.ui.screens


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.testapp.ui.components.AppBarHeight
import com.example.testapp.ui.components.ChartView
import com.example.testapp.ui.components.CircleRing
import com.example.testapp.ui.components.DailyTaskContent
import com.example.testapp.viewmodel.TaskViewModel
import com.google.accompanist.insets.statusBarsPadding

@Composable
fun TaskScreen(taskViewModel: TaskViewModel = viewModel()) {
  //0xff149ee7 0xffffffff
  var boxWidthDp: Int
  with(LocalConfiguration.current) {
    boxWidthDp = screenWidthDp / 2
  }
  //更新学年积分
  LaunchedEffect(key1 = taskViewModel.pointOfYear) {
    taskViewModel.updataPointOfYearPercent()
    taskViewModel.updataTips()
  }

  Column(
    modifier = Modifier
      .background(
        Brush.verticalGradient(
          listOf(Color(0xff149ee7), Color(0xffffffff))
        )
      )
      .fillMaxSize()
  ) {
    Text(
      text = "学习任务",
      fontSize = 18.sp,
      color = Color(0xffffffff),
      modifier = Modifier
        .fillMaxWidth()
        .padding(top = 35.dp),
      textAlign = TextAlign.Center,
    )
    LazyColumn(horizontalAlignment = Alignment.CenterHorizontally) {
      item {
        Text(
          text = taskViewModel.taskData, fontSize = 12.sp,
          color = Color.White,
          modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
          textAlign = TextAlign.Center
        )
      }
      //学习进度
      item {
        Box(
          contentAlignment = Alignment.Center,
          modifier = Modifier
            .height(boxWidthDp.dp)
        ) {
          CircleRing(boxWidthDp)
          Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
              buildAnnotatedString {
                append(taskViewModel.pointOfYear.toString())
                withStyle(style = SpanStyle(fontSize = 12.sp)) {
                  append("分")
                }
              },
              fontSize = 36.sp,
              color = Color.White,
            )
            Text(
              text = "学年积分",
              fontSize = 12.sp,
              color = Color.White,
            )
          }

        }
      }
      item {
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .offset(y = (-40).dp), horizontalArrangement = Arrangement.SpaceEvenly
        ) {
          Column {
            Text(text = "${taskViewModel.totalPointOfYear}分", fontSize = 16.sp, color = Color.White)
            Text(text = "学年规定积分", fontSize = 12.sp, color = Color.White)
          }
          Column {
            Text(
              text = "${taskViewModel.totalPointOfYear - taskViewModel.pointOfYear}分",
              fontSize = 16.sp,
              color = Color.White
            )
            Text(text = "还差", fontSize = 12.sp, color = Color.White)
          }
        }
      }
      //学习明细
      item {
        Column(
          modifier = Modifier
            .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
            .background(color = Color.White)
            .fillMaxSize()
            .padding(top = 8.dp)
            .padding(horizontal = 8.dp, vertical = 8.dp)
        ) {
          Text(text = "学习明细", fontSize = 16.sp, color = Color(0xff333333))
          Spacer(modifier = Modifier.height(4.dp))
          Text(text = "最近一周获得积分情况", fontSize = 14.sp, color = Color(0xff999999))
          //积分折线图
          ChartView(modifier = Modifier.padding(vertical = 8.dp), taskViewModel.pointOfWeek)
          //日期
          Row() {
            taskViewModel.weeks.forEach {
              Text(
                text = it,
                fontSize = 12.sp,
                color = Color(0xff999999),
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center
              )
            }
          }
          //今日任务提醒
          Text(
            text = taskViewModel.tips,
            fontSize = 17.sp,
            color = Color(0xff149ee7),
            modifier = Modifier
              .padding(vertical = 8.dp)
              .clip(RoundedCornerShape(4.dp))
              .background(color = Color(0x33149ee7))
              .fillMaxWidth()
              .padding(8.dp)
          )
          //每日任务
          DailyTaskContent()

        }
      }
    }

  }


}

@Preview
@Composable
fun TaskScreenPreview() {
  TaskScreen()
}


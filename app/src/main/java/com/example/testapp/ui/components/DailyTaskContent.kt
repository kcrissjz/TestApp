package com.example.testapp.ui.components


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.HelpCenter
import androidx.compose.material.icons.filled.HelpOutline
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.PlaceholderVerticalAlign
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DailyTaskContent() {
  DailyTaskItem("登录", "", "以获5积分/每日上限5积分", 0.9f)
  DailyTaskItem("文章学习", "10积分/每有效阅读1篇文章", "以获50积分/每日上限100积分", 1f)
  DailyTaskItem("试听学习", "10积分/每有效观看视频或收听音频累计", "以获50积分/每日上限100积分", 0.9f)
  DailyTaskItem("登录", "", "以获5积分/每日上限5积分", 0.9f)
  DailyTaskItem("文章学习", "10积分/每有效阅读1篇文章", "以获50积分/每日上限100积分", 1f)
  DailyTaskItem("试听学习", "10积分/每有效观看视频或收听音频累计", "以获50积分/每日上限100积分", 0.9f)
}

@Composable
fun DailyTaskItem(title: String, secondTitle: String, desc: String, percent: Float) {
  val inlineContentId = "inlingeContentId"
  val secondaryAnnotatedText = buildAnnotatedString {
    append(secondTitle)
    appendInlineContent(inlineContentId, "[icon]")
  }
  val inlineContent = mapOf(
    Pair(
      inlineContentId,
      InlineTextContent(
        Placeholder(
          width = 14.sp,
          height = 14.sp,
          placeholderVerticalAlign = PlaceholderVerticalAlign.AboveBaseline
        )
      ) {
        Icon(
          imageVector = Icons.Default.HelpOutline,
          contentDescription = null,
        )
      }
    )
  )

  Row(
    horizontalArrangement = Arrangement.SpaceAround,
    modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
    verticalAlignment = Alignment.CenterVertically
  ) {
    Column(modifier = Modifier.weight(7.5f)) {
      Text(text = title, fontSize = 16.sp, color = Color(0xff333333))
      if (secondTitle.isNotEmpty()) {
        Text(
          text = secondaryAnnotatedText,
          inlineContent = inlineContent,
          fontSize = 14.sp,
          color = Color(0xff333333)
        )
      }
      Row(verticalAlignment = Alignment.CenterVertically) {
        LinearProgressIndicator(progress = percent, modifier = Modifier.weight(3f))
        Text(
          text = desc,
          fontSize = 10.sp,
          color = Color(0xff666666),
          modifier = Modifier.weight(7f, fill = false).padding(horizontal = 8.dp)
        )
      }
    }
    OutlinedButton(
      onClick = { },
      border = if (percent >= 1) ButtonDefaults.outlinedBorder else BorderStroke(
        1.dp,
        color = Color(0xffff5900)
      ),
      shape = CircleShape,
      colors = ButtonDefaults.outlinedButtonColors(contentColor = Color(0xffff5900)),
      modifier = Modifier.weight(2.5f),
      enabled = (percent < 1f)
    ) {

        Text(text = if(percent<1) "去学习" else "已完成")
    }

  }
}


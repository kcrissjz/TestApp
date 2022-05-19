package com.example.testapp.ui.components


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.testapp.model.entity.ArticleEntity

@Composable
fun ArticleItem(article: ArticleEntity,modifier:Modifier = Modifier) {
  Column(modifier = modifier.padding(8.dp)) {
    Text(
      buildAnnotatedString {
        withStyle(
          style = SpanStyle(
            fontSize = 16.sp,
            color = Color(0xff333333),
          )
        ) {
          append(if (article.tag == "1") "重要" else "紧急")
        }
        append(article.title)
      },

      maxLines = 2,
      overflow = TextOverflow.Ellipsis
    )

    Text(
      text = "来源:${article.source}      ${article.timestamp}",
      fontSize = 10.sp,
      color = Color(0xff999999),
      modifier = Modifier.padding(top = 8.dp)
    )
    Divider(modifier = Modifier.padding(top = 8.dp))
  }

}

@Preview
@Composable
fun ArticleItemPreview() {
  //  ArticleItem()
}


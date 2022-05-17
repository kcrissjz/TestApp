package com.example.testapp.ui.components


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.testapp.model.entity.ArticleEntity

@Composable
fun ArticleItem(article: ArticleEntity) {
    Column(modifier = Modifier.padding(8.dp)) {
        Text(
            text = article.title,
            fontSize = 16.sp,
            color = Color(0xff333333),
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


package com.example.testapp.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.testapp.model.entity.ArticleEntity

class ArticleViewModel : ViewModel() {

  val list = listOf(
    ArticleEntity(
      title = "人社部向疫情防控期参与复工复产的劳动者表1",
      source = "“学习强国”学习平台",
      timestamp = "2020-02-10"
    ),
    ArticleEntity(
      title = "人社部向疫情防控期参与复工复产的劳动者表人社部向疫情防控期参与复工复产的劳动者表2",
      source = "“学习强国”学习平台",
      timestamp = "2020-02-11"
    ), ArticleEntity(
      title = "人社部向疫情防控期参与复工复产的劳动者表3",
      source = "“学习强国”学习平台",
      timestamp = "2020-02-12"
    ), ArticleEntity(
      title = "人社部向疫情防控期参与复工复产的劳动者表4",
      source = "“学习强国”学习平台",
      timestamp = "2020-02-13"
    ), ArticleEntity(
      title = "人社部向疫情防控期参与复工复产的劳动者表5",
      source = "“学习强国”学习平台",
      timestamp = "2020-02-14"
    ), ArticleEntity(
      title = "人社部向疫情防控期参与复工复产的劳动者表6",
      source = "“学习强国”学习平台",
      timestamp = "2020-02-15"
    ), ArticleEntity(
      title = "人社部向疫情防控期参与复工复产的劳动者表7",
      source = "“学习强国”学习平台",
      timestamp = "2020-02-16"
    ), ArticleEntity(
      title = "人社部向疫情防控期参与复工复产的劳动者表8",
      source = "“学习强国”学习平台",
      timestamp = "2020-02-17"
    ), ArticleEntity(
      title = "人社部向疫情防控期参与复工复产的劳动者表9",
      source = "“学习强国”学习平台",
      timestamp = "2020-02-18"
    ), ArticleEntity(
      title = "人社部向疫情防控期参与复工复产的劳动者表0",
      source = "“学习强国”学习平台",
      timestamp = "2020-02-19"
    )
  )


  //HTML 头部
  private val htmlHeader = """
        <!DOCTYPE html>
        <html lang="en">
        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title></title>
            <style>
                img {
                    max-width: 100% !important;
                }
            </style>
        </head>
        <body>
    """.trimIndent()

  //html尾部
  private val htmlFooter = """
        </body>
        </html>
    """.trimIndent()

  private var articleEntity: ArticleEntity? = null

  var content by mutableStateOf(
    """$htmlHeader
        ${articleEntity?.content ?: "xxx"}
        $htmlFooter
    """.trimIndent()
  )

}
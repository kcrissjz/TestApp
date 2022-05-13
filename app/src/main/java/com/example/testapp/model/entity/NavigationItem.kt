package com.example.testapp.model.entity

import androidx.compose.material.icons.Icons
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * 导航栏对象
 */
data class NavigationItem(
  var title: String,
  var icon: ImageVector
)
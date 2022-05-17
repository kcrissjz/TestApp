package com.example.testapp.ui.components


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.VideoCall
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil.compose.AsyncImage
import com.example.testapp.model.entity.VideoEntity

@Composable
fun VideoItem(video: VideoEntity) {
  ConstraintLayout(
    modifier = Modifier
      .fillMaxWidth()
      .padding(8.dp)
  ) {
    val (img, title, icon, type, time, divi) = createRefs()
    AsyncImage(
      model = video.imageUrl,
      contentDescription = null,
      contentScale = ContentScale.Crop,
      modifier = Modifier
        .aspectRatio(15/9f)
        .clip(RoundedCornerShape(8.dp))
        .constrainAs(img) {
          width= Dimension.value(115.dp)
          start.linkTo(parent.start)
          centerVerticallyTo(parent)
        }
    )
    Text(
      text = video.title,
      color = Color(0xff333333),
      fontSize = 16.sp,
      maxLines = 2,
      overflow = TextOverflow.Ellipsis,
      modifier = Modifier
        .constrainAs(title) {
          width = Dimension.fillToConstraints
          start.linkTo(img.end, margin = 8.dp)
          top.linkTo(img.top)
          end.linkTo(parent.end)
        }
    )
    Icon(
      imageVector = Icons.Default.VideoCall,
      contentDescription = null,
      modifier = Modifier
        .constrainAs(icon) {
          start.linkTo(img.end)
          bottom.linkTo(img.bottom)
        }
        .padding(start = 8.dp)
    )
    Text(
      text = video.type,
      fontSize = 10.sp,
      color = Color(0xff999999),
      modifier = Modifier
        .constrainAs(type) {
          start.linkTo(icon.end)
          bottom.linkTo(icon.bottom)
          top.linkTo(icon.top)
        }
        .padding(start = 4.dp)
    )
    Text(
      text = "时长:${video.duration}",
      fontSize = 10.sp,
      color = Color(0xff999999),
      modifier = Modifier
        .constrainAs(time) {
          start.linkTo(type.end)
          top.linkTo(type.top)
          bottom.linkTo(type.bottom)
        }
        .padding(start = 16.dp))

    Divider(modifier = Modifier.constrainAs(divi){
      top.linkTo(parent.bottom, margin = 8.dp)
    })

  }

}


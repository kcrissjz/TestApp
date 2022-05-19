package com.example.testapp.ui.components


import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.testapp.viewmodel.TaskViewModel

@Composable
fun CircleRing(boxWidthDp:Int,vm:TaskViewModel= viewModel()) {
  var strikeWidth = 30f
  Canvas(modifier = Modifier.size(boxWidthDp.dp)){
    drawArc(
      Color(0,0,0,15),
      160f,
      220f,
      false,
    style = Stroke(strikeWidth, cap = StrokeCap.Round))
    drawArc(
      Color(0xffffffff),
      160f,
      vm.pointOfYearPercent,
      false,
      style = Stroke(strikeWidth, cap = StrokeCap.Round))
  }

}

@Preview
@Composable
fun CircleRingPreview() {
//  CircleRing()
}


package com.example.testapp.ui.screens


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.filled.ArrowLeft
import androidx.compose.material.icons.filled.TextFields
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.testapp.ui.components.TopAppBar
import com.example.testapp.viewmodel.ArticleViewModel
import kotlinx.coroutines.launch
import org.kcriss.mywebview.MyWebView
import org.kcriss.mywebview.rememberWebViewState

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ArticleDetailScreen(onBack: () -> Unit = {}, vm: ArticleViewModel = viewModel()) {

  val webViewState = rememberWebViewState(data = vm.content)
  var fontScale by remember {
    mutableStateOf(1.0f)
  }
  val scaffildState = rememberBottomSheetScaffoldState()
  val coroutineScope = rememberCoroutineScope()


  BottomSheetScaffold(scaffoldState = scaffildState,sheetContent = {
    Column(modifier = Modifier.padding(18.dp)) {
      Text(text = "字体大小")
      Slider(
        value = fontScale, onValueChange = {
          fontScale = it
          webViewState.evaluateJavascript("document.body.style.zoom=${it}")
        }, steps = 3, valueRange = 0.75f..1.75f
      )
      Row (horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()){
        Text(text = "较小")
        Text(text = "标准")
        Text(text = "普大")
        Text(text = "超大")
        Text(text = "特大")
      }
    }
  }, sheetPeekHeight = 0.dp) {
    TopAppBar() {
      ConstraintLayout(modifier = Modifier.fillMaxHeight()) {
        val (back, title, action) = createRefs()
        Icon(
          imageVector = Icons.Default.ArrowBackIos,
          contentDescription = null,
          tint = Color(0xffffffff),
          modifier = Modifier
            .size(19.dp)
            .constrainAs(back) {
              start.linkTo(parent.start, margin = 16.dp)
              centerVerticallyTo(parent)
            }
            .clickable { onBack() }
        )

        Text(
          text = "文章详情",
          color = Color.White,
          textAlign = TextAlign.Center,
          modifier = Modifier
            .fillMaxWidth()
            .constrainAs(title) {
              centerTo(parent)
            }
        )
        Icon(
          imageVector = Icons.Default.TextFields,
          contentDescription = null,
          tint = Color.White,
          modifier = Modifier
            .constrainAs(action) {
              end.linkTo(parent.end, margin = 16.dp)
              centerVerticallyTo(parent)
            }
            .clickable {
              coroutineScope.launch {
                if ( scaffildState.bottomSheetState.isCollapsed){
                  scaffildState.bottomSheetState.expand()
                }else{
                  scaffildState.bottomSheetState.collapse()
                }
              }
            })

      }


    }
    MyWebView(webViewState)
  }

}


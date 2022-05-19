package com.example.testapp.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class TaskViewModel:ViewModel() {

  var taskData by mutableStateOf("学习周期:2022:01.01 - 2022.12.31")
    private set
  val totalPointOfYear = 13500
  //学年积分
  var pointOfYear by mutableStateOf(9000)
  var pointOfYearPercent by mutableStateOf(0f)

  fun updataPointOfYearPercent(){
    pointOfYearPercent = 220f * pointOfYear / totalPointOfYear
  }


  var pointOfWeek by mutableStateOf(listOf(0.0,13.1,6.0,2.5,10.0,15.0,5.0))

  val weeks = listOf("02.05","02.06","02.07","02.08","02.09","02.10","今日")

  private var todayPoint =12
  var tips by mutableStateOf("今日获得0积分快去完成下面的任务吧")
    private set

  fun updataTips(){
    if (todayPoint ==0 ){
      tips = "今日获得${todayPoint}积分快去完成下面的任务吧"
    }else if(todayPoint in 1..14){
      tips = "今日获得${todayPoint}积分快去完成下面的任务吧"
    }else{
      tips = "今日已完成任务"
    }
  }
}
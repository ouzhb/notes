package com.quick_scala.exercies.chapter5

import scala.beans.BeanProperty

class Student {
  @BeanProperty
  var name: String = "my name"
  @BeanProperty
  var id: Long = 0
}

object Student {
  def main(args: Array[String]): Unit = {
    val student = new Student
    student.setId(10000)
    println(student.getId())

  }
}
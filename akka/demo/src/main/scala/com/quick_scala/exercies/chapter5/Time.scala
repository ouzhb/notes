package com.quick_scala.exercies.chapter5

class Time(val hours: Int, val minutes: Int) {
  def before(other: Time): Boolean = (hours < other.hours) || minutes < other.minutes

}

object Time {
  def main(args: Array[String]): Unit = {
    val time1 = new Time(1, 20)
    val time2 = new Time(2, 30)
    println(time2.before(time1))
  }
}
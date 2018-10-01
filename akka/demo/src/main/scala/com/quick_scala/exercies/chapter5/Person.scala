package com.quick_scala.exercies.chapter5

class Person(val full_name:String = "Fred Smith") {
  val firstName = full_name.split(" ")(0)
  val lastName = full_name.split(" ")(1)
}

object Person {
  def main(args: Array[String]): Unit = {
    val person = new Person()
    println(person.firstName)
  }
}

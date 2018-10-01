package com.quick_scala.exercies.chapter5

class Counter {
  private var value = 0

  def increment() {
    if (value < Int.MaxValue)
      value += 1
  }

  def current() = value
}

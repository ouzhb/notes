package com.quick_scala.exercies.chapter5

class BankAccount {
  def deposit() = {
    println("Deposit!!")
  }

  def withdraw(): Unit = {
    println("Withdraw")
    println(balance2)
  }

  // 伴生对象可以访问
  private var balance_value: Int = 0
  private[this] val balance2: Int = 1

  def balance: Int = {
    println("get balance")
    balance_value
  }

  def balance_=(value: Int): Unit = {
    println("set balance")
    balance_value = value
  }
}

object BankAccount {
  def main(args: Array[String]): Unit = {
    val bankAccount = new BankAccount
    bankAccount.deposit()
    bankAccount.withdraw()
    bankAccount.balance= 100
    println(bankAccount.balance)
  }
}
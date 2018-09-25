package com.lightbend.akka.sample

import akka.actor.{Actor, ActorSystem, Props}

import scala.io.StdIn // for scala 2.12

class PrintMyActorRefActor extends Actor {
  // 该方法在处理第一个消息时被调用
  override def preStart(): Unit = println("first started")
  // 调用postStop后该actor即停止工作
  override def postStop(): Unit = println("first stopped")
  override def receive: Receive = {
    // 当收到的消息为printit时，创建一个子actor
    case "printit" ⇒
      val secondRef = context.actorOf(Props.empty, "second-actor")
      println(s"Second: $secondRef")
  }
}

object ActorHierarchyExperiments extends App {
  //创建一个ActorSystem
  //ActorSystem一旦创建好，即包括：/,/user,/system三个actor
  val system = ActorSystem("testSystem")

  // 在/user下创建一个actor，该actor包含PrintMyActorRefActor方法
  val firstRef = system.actorOf(Props[PrintMyActorRefActor], "first-actor")

  // 这里打印了actor的地址，akka://<system path>/user/<child-actor>#<uuid>
  println(s"First: $firstRef")

  // 向firstRef发送一个消息
  firstRef ! "printit"

  println(">>> Press ENTER to exit <<<")

  // 从stdin读取一行，如果读到了就关闭system
  try StdIn.readLine()
  finally system.terminate()
}
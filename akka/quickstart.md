### 2018-09-22 中秋快乐


# 入门指南
参考：https://blog.csdn.net/wang_wbq/article/details/78845804

## Actor模型解决的问题
- 解决传统单线程编程模型中锁的问题，以及效率的问题；
- 提出一种代替堆栈的错误处理方式，以及恢复方式；
- 解决进程间共享内存的性能问题（CPU写入内存时实际上是写入本地缓存，二不同进程&CPU之间的缓存通信，比想象的要费性能）；

## Actor提供的能力
- 使用消息队列代替方法调用，由于队列同一个时间只处理一个消息，因此定义在actor内的变量，不会遇到锁问题
- 调用的响应也是通过消息实现的，并且响应在actor树内层层传递，变相实现了错误处理

## 簡單的Demo

```scala
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

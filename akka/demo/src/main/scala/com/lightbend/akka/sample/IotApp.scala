package com.lightbend.akka.sample

import akka.actor.ActorSystem
import com.lightbend.akka.sample.DeviceManager.RequestTrackDevice

import scala.io.StdIn

object IotApp {

  def main(args: Array[String]): Unit = {
    val system = ActorSystem("iot-system")

    try {
      // 顶一个IotSupervisor作为一个顶级的actor
//      val supervisor = system.actorOf(IotSupervisor.props(), "iot-supervisor")
      val deviceManager = system.actorOf(DeviceManager.props(),"device-manager")
      deviceManager ! RequestTrackDevice("group1","device-A")
      deviceManager ! RequestTrackDevice("group1","device-A")
      deviceManager ! RequestTrackDevice("group2","device-B")


      // Exit the system after ENTER is pressed
      StdIn.readLine()
    } finally {
      system.terminate()
    }
  }

}

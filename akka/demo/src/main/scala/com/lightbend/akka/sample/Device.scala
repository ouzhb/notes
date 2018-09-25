package com.lightbend.akka.sample
import akka.actor.{ Actor, ActorLogging, Props }

object Device {
  def props(groupId: String, deviceId: String): Props = Props(new Device(groupId, deviceId))

  // 定义了以下两个消息？ case关键字啥意思？？
  // requestId使请求和响应匹配
  final case class ReadTemperature(requestId: Long)
  final case class RespondTemperature(requestId: Long, value: Option[Double])

  // 下面的消息用来写入Device的温度信息
  final case class RecordTemperature(requestId: Long, value: Double)
  final case class TemperatureRecorded(requestId: Long)

}

class Device(groupId: String, deviceId: String) extends Actor with ActorLogging {
  import Device._

  var lastTemperatureReading: Option[Double] = None

  override def preStart(): Unit = log.info("Device actor {}-{} started", groupId, deviceId)
  override def postStop(): Unit = log.info("Device actor {}-{} stopped", groupId, deviceId)

  override def receive: Receive = {
    // 设备接收到ReadTemperature消息时通过sender接口发送RespondTemperature
    // sender 应该是预定义的回传接口
    case ReadTemperature(id) ⇒
      sender() ! RespondTemperature(id, lastTemperatureReading)

    case RecordTemperature(id, value) ⇒
      log.info("Recorded temperature reading {} with {}", value, id)
      lastTemperatureReading = Some(value)
      sender() ! TemperatureRecorded(id)

      // 頭上加``視乎表示對象的屬性？？ fuck 什麽鬼
    case DeviceManager.RequestTrackDevice(`groupId`, `deviceId`) ⇒
      // 如果groupId和deviceId是匹配的，那麽返回DeviceManager.DeviceRegistered
      sender() ! DeviceManager.DeviceRegistered

    case DeviceManager.RequestTrackDevice(groupId, deviceId) ⇒
      log.warning(
        "Ignoring TrackDevice request for {}-{}.This actor is responsible for {}-{}.",
        groupId, deviceId, this.groupId, this.deviceId
      )
  }

}

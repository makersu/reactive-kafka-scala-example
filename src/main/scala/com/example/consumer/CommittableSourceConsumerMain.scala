package com.example.consumer

import akka.actor.ActorSystem
import akka.kafka.scaladsl.Consumer
import akka.kafka.{ConsumerSettings, Subscriptions}
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.Sink
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.{ByteArrayDeserializer, StringDeserializer}

/**
  * Created by marksu on 8/31/16.
  */
object CommittableSourceConsumerMain extends App {

  implicit val system = ActorSystem("CommittableSourceConsumerMain")
  implicit val materializer = ActorMaterializer()

  //TODO: move to configuration application.conf
  val consumerSettings =
    ConsumerSettings(system, new ByteArrayDeserializer, new StringDeserializer)
      .withBootstrapServers("localhost:9092")
      .withGroupId("CommittableSourceConsumer")
      .withProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest")

  val done =
    Consumer.committableSource(consumerSettings, Subscriptions.topics("topic1"))
      .mapAsync(1) { msg =>
        println(s"CommittableSourceConsumer consume: $msg")
        msg.committableOffset.commitScaladsl()
      }
      .runWith(Sink.ignore)

}

package com.example.consumer

import akka.Done
import akka.actor.ActorSystem
import akka.kafka.ConsumerMessage.CommittableOffsetBatch
import akka.kafka.scaladsl.Consumer
import akka.kafka.{ConsumerSettings, Subscriptions}
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.Sink
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.{ByteArrayDeserializer, StringDeserializer}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
/**
  * Created by marksu on 8/31/16.
  */
object BatchCommittableSourceConsumerMain extends App {
  implicit val system = ActorSystem("BatchCommittableSourceConsumerMain")
  implicit val materializer = ActorMaterializer()

  //TODO: move to configuration application.conf
  val consumerSettings = ConsumerSettings(system, new ByteArrayDeserializer, new StringDeserializer)
    .withBootstrapServers("localhost:9092")
    .withGroupId("BatchCommittableSourceConsumer")
    .withProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest")

  val done =
    Consumer.committableSource(consumerSettings, Subscriptions.topics("topic1"))
      .mapAsync(1) { msg =>
        println(s"BatchCommittableConsumer consume: $msg")
        Future.successful(Done).map(_ => msg.committableOffset)
      }
      .batch(max = 20, first => CommittableOffsetBatch.empty.updated(first)) { (batch, elem) =>
        batch.updated(elem)
      }
      .mapAsync(3)(_.commitScaladsl())
      .runWith(Sink.ignore)

}

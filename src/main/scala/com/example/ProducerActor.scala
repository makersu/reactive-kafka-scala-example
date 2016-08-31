package com.example

import akka.actor.{Actor, ActorLogging, Props}
import akka.kafka.ProducerSettings
import akka.kafka.scaladsl.Producer
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.Source
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.common.serialization.{ByteArraySerializer, StringSerializer}

/**
  * Created by marksu on 8/31/16.
  */
class ProducerActor extends Actor with ActorLogging {
  implicit val materializer = ActorMaterializer()

  val producerSettings = ProducerSettings(context.system, new ByteArraySerializer, new StringSerializer)
    .withBootstrapServers("localhost:9092")

  def receive = {
    case "start" =>
      log.info("ProducerActor received start")
      val done = Source(1 to 10)
        .map(_.toString)
        .map { elem =>
          log.info(s"ProducerActor produce: ${elem}")
          new ProducerRecord[Array[Byte], String]("topic1", elem)
        }
        .runWith(Producer.plainSink(producerSettings))

    case _ => log.info("received unknown message")
  }
}

object ProducerActor {
  def props = Props[ProducerActor]
}

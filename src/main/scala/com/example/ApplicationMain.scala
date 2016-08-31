package com.example

import akka.actor.ActorSystem

object ApplicationMain extends App {
  val system = ActorSystem("ActorSystem")
//  val pingActor = system.actorOf(PingActor.props, "pingActor")
//  pingActor ! PingActor.Initialize
//  // This example app will ping pong 3 times and thereafter terminate the ActorSystem -
//  // see counter logic in PingActor
//  system.awaitTermination()

  val producerActor = system.actorOf(ProducerActor.props)
  producerActor ! "start"


}
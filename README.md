reactive-kafka-scala-example
=========================
reactive-kafka examples in scala

Akka Streams Kafka: http://doc.akka.io/docs/akka-stream-kafka/current/home.html

### Install Kafka on OSX
```
brew install kafka
```

### Start Kafka
```
zkserver start
kafka-server-start /usr/local/etc/kafka/server.properties
```

### Start a consumer on console for topic1/topic2
```
kafka-console-consumer --zookeeper localhost:2181 --topic topic1  --from-beginning
kafka-console-consumer --zookeeper localhost:2181 --topic topic2  --from-beginning
```

### Start a producer on console for topic1
```
kafka-console-producer --broker-list localhost:9092 --topic topic1
```
===
### Start reactive-kafka examples
```
git clone https://github.com/makersu/reactive-kafka-scala-example.git
cd reactive-kafka-scala-example
sbt run
```
```
Multiple main classes detected, select one to run:

 [1] com.example.consumer.BatchCommittableSourceConsumerMain
 [2] com.example.consumer.CommittableSourceConsumerMain
 [3] com.example.consumer.PlainSourceConsumerMain
 [4] com.example.producer.CommitConsumerToFlowProducerMain
 [5] com.example.producer.ConsumerToCommitableSinkProducerMain
 [6] com.example.producer.FlowProducerMain
 [7] com.example.producer.PlainSinkProducerMain

Enter number:
```




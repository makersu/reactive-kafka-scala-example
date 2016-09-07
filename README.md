reactive-kafka-scala-example
=========================
reactive-kafka examples in scala

### Install Kafka on OSX
```
brew install kafka
```

### Start Kafka
```
zkserver start
kafka-server-start /usr/local/etc/kafka/server.properties
```

### Start a consumer on console
```
kafka-console-consumer --zookeeper localhost:2181 --topic topic1  --from-beginning
kafka-console-consumer --zookeeper localhost:2181 --topic topic2  --from-beginning
```

### Start a producer on console
```
kafka-console-producer --broker-list localhost:9092 --topic topic1
```



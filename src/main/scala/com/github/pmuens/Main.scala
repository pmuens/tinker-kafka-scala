package com.github.pmuens

import java.util
import java.util.Properties

import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}
import org.apache.kafka.streams.scala.Serdes._
import org.apache.kafka.streams.scala.ImplicitConversions._
import org.apache.kafka.streams.KafkaStreams
import org.apache.kafka.streams.scala.kstream._
import org.apache.kafka.streams.scala.StreamsBuilder

import scala.collection.JavaConverters._

object Main {
  val server = "localhost:9092"
  val stringSerializer = "org.apache.kafka.common.serialization.StringSerializer"
  val stringDeserializer = "org.apache.kafka.common.serialization.StringDeserializer"

  def writeToKafka(topic: String, key: String, value: String): Unit = {
    val props = new Properties()
    props.put("bootstrap.servers", server)
    props.put("key.serializer", stringSerializer)
    props.put("value.serializer", stringSerializer)

    val producer = new KafkaProducer[String, String](props)
    val record = new ProducerRecord[String, String](topic, key, value)

    producer.send(record)
    producer.close()
  }

  def consumeFromKafka(topic: String): Unit = {
    val props = new Properties()
    props.put("bootstrap.servers", server)
    props.put("key.deserializer", stringDeserializer)
    props.put("value.deserializer", stringDeserializer)
    props.put("auto.offset.reset", "latest")
    props.put("group.id", "consumer-group")

    val consumer: KafkaConsumer[String, String] = new KafkaConsumer[String, String](props)
    consumer.subscribe(util.Arrays.asList(topic))

    while (true) {
      val record = consumer.poll(1000).asScala
      for (data <- record.iterator) {
        println(data.value())
      }
    }
  }

  def kafkaStreamForwarder(appId: String, sourceTopic: String, sinkTopic: String): Unit = {
    val config = {
      val props = new Properties()
      props.put("application.id", appId)
      props.put("bootstrap.servers", server)
      props
    }

    val builder = new StreamsBuilder()

    val sourceStream = builder.stream[String, String](sourceTopic)
    sourceStream.to(sinkTopic)

    val stream = new KafkaStreams(builder.build(), config)
    stream.cleanUp()
    stream.start()
  }

  def kafkaStreamWordCounter(appId: String, sourceTopic: String, sinkTopic: String): Unit ={
    val config= {
      val props = new Properties()
      props.put("application.id", appId)
      props.put("bootstrap.servers", server)
      props.put("print.key", "true")
      props
    }

    val builder = new StreamsBuilder()

    val sourceStream: KStream[String, String] = builder.stream[String, String](sourceTopic)
    val wordCounts: KTable[String, Long] = sourceStream
      .flatMapValues(line => line.toLowerCase().split("\\W+"))
      .groupBy((_, word) => word)
      .count()
    wordCounts.toStream.to(sinkTopic)

    val stream = new KafkaStreams(builder.build(), config)
    stream.cleanUp()
    stream.start()
  }
}

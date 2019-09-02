package com.github.pmuens

import java.util
import java.util.Properties

import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}

import scala.collection.JavaConverters._

object Main {
  val server = "localhost:9092"
  val serializer = "org.apache.kafka.common.serialization.StringSerializer"
  val deserializer = "org.apache.kafka.common.serialization.StringDeserializer"

  def writeToKafka(topic: String, key: String, value: String): Unit = {
    val props = new Properties()
    props.put("bootstrap.servers", server)
    props.put("key.serializer", serializer)
    props.put("value.serializer", serializer)

    val producer = new KafkaProducer[String, String](props)
    val record = new ProducerRecord[String, String](topic, key, value)

    producer.send(record)
    producer.close()
  }

  def consumeFromKafka(topic: String): Unit = {
    val props = new Properties()
    props.put("bootstrap.servers", server)
    props.put("key.deserializer", deserializer)
    props.put("value.deserializer", deserializer)
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
}

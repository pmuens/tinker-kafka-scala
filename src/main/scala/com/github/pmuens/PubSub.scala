package com.github.pmuens

import com.github.pmuens.Main.writeToKafka
import com.github.pmuens.Main.consumeFromKafka

// 2. Run the producer (watch the consumers log output)
object Produce extends App {
  override def main(args: Array[String]): Unit = {
    val topic = "kafka-test"

    writeToKafka(topic, "message", "Hello World!")
  }
}

// 1. Run the consumer
object Consume extends App{
  override def main(args: Array[String]): Unit = {
    val topic = "kafka-test"

    consumeFromKafka(topic)
  }
}



package com.github.pmuens

import com.github.pmuens.Main.writeToKafka
import com.github.pmuens.Main.consumeFromKafka

object Produce extends App {
  override def main(args: Array[String]): Unit = {
    val topic = "kafka-test"

    writeToKafka(topic, "message", "Hello World!")
  }
}

object Consume extends App{
  override def main(args: Array[String]): Unit = {
    val topic = "kafka-test"

    consumeFromKafka(topic)
  }
}



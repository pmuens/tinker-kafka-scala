package com.github.pmuens

import com.github.pmuens.Main.writeToKafka
import com.github.pmuens.Main.consumeFromKafka
import com.github.pmuens.Main.kafkaStreamForwarder
import com.github.pmuens.Main.kafkaStreamWordCounter

// 3. Run the producer (watch the consumers log output)
object Producer extends App {
  override def main(array: Array[String]): Unit = {
    writeToKafka("streams-source", "message", "Hello World!")
  }
}

// 1. Run the forwarder
object Forwarder extends App {
  override def main(array: Array[String]): Unit = {
    kafkaStreamForwarder("streams-forwarder", "streams-source", "streams-sink")
  }
}

// 1. OR run the word counter
object WordCounter extends App {
  override def main(array: Array[String]): Unit = {
    kafkaStreamWordCounter("streams-word-counter", "streams-source", "streams-sink")
  }
}

// 2. Run the consumer
object Consumer extends App {
  consumeFromKafka("streams-sink")
}

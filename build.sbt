name := "tinker-kafka-scala"

version := "0.1"

scalaVersion := "2.12.8"

resolvers ++= Seq(
  "Maven Central" at "https://repo1.maven.org/maven2/",
  "Confluent" at "http://packages.confluent.io/maven/"
)

libraryDependencies ++= Seq(
  "org.apache.kafka" % "kafka_2.12" % "2.3.0",
  "org.apache.kafka" % "kafka-streams" % "2.3.0",
  "org.apache.kafka" % "kafka-streams-scala_2.12" % "2.3.0",
  "io.confluent.ksql" % "ksql-udf" % "5.3.0",
  "org.scalatest" %% "scalatest" % "3.0.8",
  "org.slf4j" % "slf4j-simple" % "1.6.4"
)

#!/bin/sh

for topic in pubsub-test streams-source streams-sink; do
  docker exec kafka kafka-topics --create --zookeeper zookeeper:2181 --topic "$topic" --partitions 1 --replication-factor 1
done

#!/bin/sh

docker exec kafka kafka-console-consumer --topic "$1" \
  --from-beginning --bootstrap-server localhost:9092 \
  --property print.key=true \
  --property value.deserializer=org.apache.kafka.common.serialization."$2"

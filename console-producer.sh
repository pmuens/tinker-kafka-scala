#!/bin/sh

docker exec -it kafka kafka-console-producer --broker-list localhost:9092 \
  --topic "$1"

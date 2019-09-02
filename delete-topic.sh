#!/bin/sh

docker exec kafka kafka-topics --delete --zookeeper zookeeper:2181 --topic "$1"

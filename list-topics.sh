#!/bin/sh

docker exec kafka kafka-topics --list --zookeeper zookeeper:2181

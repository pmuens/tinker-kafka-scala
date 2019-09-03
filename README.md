# Tinker Kafka Scala

Tinkering around with Kafka and Scala.

## Local development

1. `docker-compose up`
1. Configure Kafka topics (see the `.sh` scripts in the root directory)
1. Run the Scala code

## KSQL Queries

```sql
SHOW tables;

SHOW streams;

DESCRIBE FUNCTION <function-name>;

CREATE STREAM messages (message VARCHAR) WITH (KAFKA_TOPIC='streams-sink', VALUE_FORMAT='DELIMITED');

DROP STREAM <stream-name>;

SELECT * FROM <stream-or-table-name>;

SELECT message, formatter(message) FROM messages;
```

## Misc

```sh
docker-compose logs ksql-server | grep UDF
```

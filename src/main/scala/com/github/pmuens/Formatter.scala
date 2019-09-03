package com.github.pmuens

// This is an example for a UDF which can be used via KSQL
// `SELECT formatter(message) FROM <stream-name>;`

import io.confluent.ksql.function.udf.Udf
import io.confluent.ksql.function.udf.UdfDescription

@UdfDescription(name = "formatter", description ="formats a message")
class Formatter {
  @Udf(description = "format the message")
  def formatter(message: String): String = {
    println("UDF \"formatter\" starting...")

    s"<formatted>${message}</formatted>"
  }
}

package com.sksamuel.elastic4s.search.aggs

class MissingAggregationTcpTest extends AbstractAggregationTest {

  "missing aggregation" - {
    "should return documents missing a value" in {
      val resp = client.execute {
        search("aggregations/breakingbad") aggregations {
          aggregation missing "agg1" field "actor"
        }
      }.await
      resp.totalHits shouldBe 10
      val aggs = resp.aggregations.missingResult("agg1")
      aggs.getDocCount shouldBe 7
    }
  }
}

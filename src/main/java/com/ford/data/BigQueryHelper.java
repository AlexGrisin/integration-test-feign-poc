package com.ford.data;

import com.google.cloud.bigquery.BigQuery;
import com.google.cloud.bigquery.BigQueryOptions;
import com.google.cloud.bigquery.QueryJobConfiguration;
import com.google.cloud.bigquery.TableResult;

public class BigQueryHelper {
    private final BigQuery bigQuery;

    public BigQueryHelper() {
        this.bigQuery = BigQueryOptions.getDefaultInstance().getService();
    }

    public TableResult runQuery(String query) throws Exception {
        QueryJobConfiguration queryConfig = QueryJobConfiguration.newBuilder(query).build();
        return bigQuery.query(queryConfig);
    }
}

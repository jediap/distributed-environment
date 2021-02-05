package com.jediap.infrastructure.elastic.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;

public class ElasticSearchClient {

    public RestHighLevelClient client(String connection) {
        ClientConfiguration clientConfiguration =
                ClientConfiguration.builder()
                        .connectedTo(connection)
                        .build();

        return RestClients.create(clientConfiguration).rest();
    }
}

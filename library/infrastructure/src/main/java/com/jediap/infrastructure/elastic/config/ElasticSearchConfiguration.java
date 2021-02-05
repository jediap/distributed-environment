package com.jediap.infrastructure.elastic.config;

import com.jediap.infrastructure.context.SpringContextParameters;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;

@Configuration
public class ElasticSearchConfiguration {

  private final SpringContextParameters parameter;

  public ElasticSearchConfiguration(SpringContextParameters parameter) {
    this.parameter = parameter;
  }

  @Bean
  public RestHighLevelClient client() {
    return new ElasticSearchClient().client(parameter.ELASTIC_HOST_DB + ":" + parameter.ELASTIC_PORT_DB);
  }

  @Bean
  public ElasticsearchRestTemplate elasticsearchTemplate() {
    return new ElasticsearchRestTemplate(client());
  }
}

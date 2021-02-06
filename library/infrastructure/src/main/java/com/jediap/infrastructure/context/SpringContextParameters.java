package com.jediap.infrastructure.context;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@EnableConfigurationProperties
@Configuration
public class SpringContextParameters {
  @Value("${elastic.host}")
  public String ELASTIC_HOST_DB;

  @Value("${elastic.port}")
  public String ELASTIC_PORT_DB;

  @Value("${elastic.index}")
  public String ELASTIC_INDEX;

  @Value("${elastic.user}")
  public String ELASTIC_USER;

  @Value("${elastic.password}")
  public String ELASTIC_PASS;

  @Value("${grpc.server.port}")
  public Integer GRPC_SERVER_PORT;

  @Value("${mongo.authentication_database}")
  public String EVENT_STORE_AUTHENTICATION_DATABASE;

  @Value("${mongo.host}")
  public String EVENT_STORE_HOST_DB;

  @Value("${mongo.port}")
  public String EVENT_STORE_PORT_DB;

  @Value("${mongo.db_name}")
  public String EVENT_STORE_NAME_DB;

  @Value("${mongo.user}")
  public String EVENT_STORE_USER;

  @Value("${mongo.pass}")
  public String EVENT_STORE_PASS;
}

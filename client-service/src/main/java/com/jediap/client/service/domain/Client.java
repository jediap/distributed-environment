package com.jediap.client.service.domain;

import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "client_client")
public class Client {

  private String id;
  private String name;

  public Client(final String id, final String name) {
    this.id = id;
    this.name = name;
  }

  public String id() {
    return id;
  }

  public String name() {
    return name;
  }
}

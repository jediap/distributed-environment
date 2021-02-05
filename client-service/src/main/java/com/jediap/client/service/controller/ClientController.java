package com.jediap.client.service.controller;

import com.jediap.client.service.domain.Client;
import com.jediap.infrastructure.elastic.ElasticsearchRepository;
import com.jediap.infrastructure.mongodb.MongoDbEventStorePublisher;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
public class ClientController {

  private static final String CLIENT_INDEX = "client_client";

  @Value("${server.port}")
  private String port;

  private final ElasticsearchRepository elasticsearchRepository;
  private final MongoDbEventStorePublisher mongoDbEventStorePublisher;

  public ClientController(ElasticsearchRepository elasticsearchRepository, MongoDbEventStorePublisher mongoDbEventStorePublisher){
    this.mongoDbEventStorePublisher = mongoDbEventStorePublisher;
    this.elasticsearchRepository = elasticsearchRepository;
  }

  @RequestMapping("/actuator/hello")
  public String  clientService() {

    mongoDbEventStorePublisher.publish(UUID.randomUUID().toString(), UUID.randomUUID().toString(), "name", "occurredOn", "version", "body", "meta");

    elasticsearchRepository.save(new Client(UUID.randomUUID().toString(), UUID.randomUUID().toString()),CLIENT_INDEX);

    List<Client> list = elasticsearchRepository.find(PageRequest.of(0, 10), Client.class);

    list.stream().forEach(source -> {
      System.out.println("ID "+source.id()+" NAME "+source.name());
    });

    return "Hello to Client Service with port "+port;
  }
}

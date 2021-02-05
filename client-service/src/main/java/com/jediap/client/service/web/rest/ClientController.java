package com.jediap.client.service.web.rest;

import com.jediap.client.service.domain.Client;
import com.jediap.infrastructure.elastic.ElasticsearchRepository;
import com.jediap.infrastructure.mongodb.MongoDbEventStorePublisher;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
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

    elasticsearchRepository.persistence(new Client(UUID.randomUUID().toString(), UUID.randomUUID().toString()),CLIENT_INDEX);

    elasticsearchRepository.persistence(new Client("941125e7-afae-486e-9fc7-57b01cc5bfa4", "jesus"),CLIENT_INDEX);

    elasticsearchRepository.delete("f9a0db32-0b3f-4134-a0cd-163a9e799e54", CLIENT_INDEX);

    List<Client> list = elasticsearchRepository.find(new HashMap<>(), PageRequest.of(0, 100), Client.class);

    list.stream().forEach(source -> {
      System.out.println("ID "+source.id()+" NAME "+source.name());
    });

    return "Hello to Client Service with port "+port;
  }
}

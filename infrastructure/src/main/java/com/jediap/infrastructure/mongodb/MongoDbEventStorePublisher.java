package com.jediap.infrastructure.mongodb;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MongoDbEventStorePublisher {

  private final MongoTemplate mongoClient;

  public MongoDbEventStorePublisher(MongoTemplate mongoClient) {
    this.mongoClient = mongoClient;
  }

  @Transactional
  public void publish(
      String id,
      String aggregateId,
      String name,
      String occurredOn,
      String version,
      String body,
      String meta) {

    mongoClient.insert(
        new EventStoreMongoDocument(id, aggregateId, name, occurredOn, version, body, meta));
  }
}

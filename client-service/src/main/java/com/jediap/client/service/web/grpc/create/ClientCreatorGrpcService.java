package com.jediap.client.service.web.grpc.create;

import com.google.protobuf.Empty;
import com.jediap.client.service.proto.client.Client;
import com.jediap.client.service.proto.client.CreateClientGrpc;
import com.jediap.infrastructure.elastic.ElasticsearchRepository;
import com.jediap.infrastructure.grpc.GrpcService;
import com.jediap.infrastructure.mongodb.MongoDbEventStorePublisher;
import io.grpc.stub.StreamObserver;

@GrpcService
public class ClientCreatorGrpcService extends CreateClientGrpc.CreateClientImplBase {

  private static final String CLIENT_INDEX = "client_client";

  private final ElasticsearchRepository elasticsearchRepository;
  private final MongoDbEventStorePublisher mongoDbEventStorePublisher;

  public ClientCreatorGrpcService(
      ElasticsearchRepository elasticsearchRepository,
      MongoDbEventStorePublisher mongoDbEventStorePublisher) {
    this.mongoDbEventStorePublisher = mongoDbEventStorePublisher;
    this.elasticsearchRepository = elasticsearchRepository;
  }

  @Override
  public void create(Client request, StreamObserver<Empty> responseObserver) {

    mongoDbEventStorePublisher.publish(
        request.getId(), request.getName(), "name", "occurredOn", "version", "body", "meta");

    elasticsearchRepository.persistence(
        new com.jediap.client.service.domain.Client(request.getId(), request.getName()),
        CLIENT_INDEX);

    responseObserver.onNext(Empty.getDefaultInstance());
    responseObserver.onCompleted();
  }
}

package com.jediap.client.service.web.grpc.search;

import com.jediap.client.service.proto.client.Client;
import com.jediap.client.service.proto.client.Clients;
import com.jediap.client.service.proto.client.SearchClientGrpc;
import com.jediap.infrastructure.grpc.GrpcService;
import io.grpc.stub.StreamObserver;

@GrpcService
public class ClientSearcherGrpcService extends SearchClientGrpc.SearchClientImplBase {

  @Override
  public void all(Client request, StreamObserver<Clients> responseObserver) {

    responseObserver.onNext(null);
    responseObserver.onCompleted();
  }
}

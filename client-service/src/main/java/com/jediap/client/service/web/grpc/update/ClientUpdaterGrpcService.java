package com.jediap.client.service.web.grpc.update;

import com.google.protobuf.Empty;
import com.jediap.client.service.proto.client.Client;
import com.jediap.client.service.proto.client.UpdateClientGrpc;
import com.jediap.infrastructure.grpc.GrpcService;
import io.grpc.stub.StreamObserver;

@GrpcService
public class ClientUpdaterGrpcService extends UpdateClientGrpc.UpdateClientImplBase {

  @Override
  public void update(Client request, StreamObserver<Empty> responseObserver) {

    responseObserver.onNext(Empty.getDefaultInstance());
    responseObserver.onCompleted();
  }
}

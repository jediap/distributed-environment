package com.jediap.client.service.web.grpc.delete;

import com.google.protobuf.Empty;
import com.jediap.client.service.proto.client.Client;
import com.jediap.client.service.proto.client.DeleteClientGrpc;
import com.jediap.infrastructure.grpc.GrpcService;
import io.grpc.stub.StreamObserver;

@GrpcService
public class ClientDeleterGrpcService extends DeleteClientGrpc.DeleteClientImplBase {

  @Override
  public void delete(Client request, StreamObserver<Empty> responseObserver) {

    responseObserver.onNext(Empty.getDefaultInstance());
    responseObserver.onCompleted();
  }
}

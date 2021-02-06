package com.jediap.infrastructure.grpc.config;

import com.jediap.infrastructure.context.SpringContextParameters;
import com.jediap.infrastructure.grpc.GrpcServiceDefinition;
import com.jediap.infrastructure.grpc.GrpcServicesDefinition;
import io.grpc.ServerBuilder;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.Collection;

@Configuration
public class GrpcServerConfiguration {

  private final GrpcServicesDefinition servicesDefinition;
  private final SpringContextParameters contextParameters;

  public GrpcServerConfiguration(
      GrpcServicesDefinition servicesDefinition, SpringContextParameters contextParameters) {
    this.servicesDefinition = servicesDefinition;
    this.contextParameters = contextParameters;
  }

  public void start() throws IOException, InterruptedException {

    ServerBuilder<?> serverBuilder = ServerBuilder.forPort(contextParameters.GRPC_SERVER_PORT);

    Collection<GrpcServiceDefinition> services = servicesDefinition.findGrpcServices();

    services.forEach(
        grpcServiceDefinition -> serverBuilder.addService(grpcServiceDefinition.getDefinition()));

    serverBuilder
        // .intercept(new ErrorMiddleware(log, applicationContext))
        .build()
        .start()
        .awaitTermination();
  }
}

package com.jediap.client.service;

import com.jediap.infrastructure.grpc.config.GrpcServerConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;

@SpringBootApplication
@EnableDiscoveryClient
public class Application {

  public static void main(String[] args) throws IOException, InterruptedException {
    ConfigurableApplicationContext applicationContext =
        SpringApplication.run(Application.class, args);

    GrpcServerConfiguration grpcServerConfiguration =
        applicationContext.getBean(GrpcServerConfiguration.class);
    grpcServerConfiguration.start();
  }
}

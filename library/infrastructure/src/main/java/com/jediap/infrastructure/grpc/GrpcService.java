package com.jediap.infrastructure.grpc;

import org.springframework.stereotype.Service;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
@Service
public @interface GrpcService {}

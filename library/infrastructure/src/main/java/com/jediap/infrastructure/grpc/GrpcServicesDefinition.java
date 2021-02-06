package com.jediap.infrastructure.grpc;

import com.google.common.collect.Lists;
import io.grpc.BindableService;
import io.grpc.ServerServiceDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

@Service
public class GrpcServicesDefinition {

  private final ApplicationContext applicationContext;

  public GrpcServicesDefinition(ApplicationContext applicationContext) {
    this.applicationContext = applicationContext;
  }

  public Collection<GrpcServiceDefinition> findGrpcServices() {
    Collection<String> beanNames =
        Arrays.asList(this.applicationContext.getBeanNamesForAnnotation(GrpcService.class));
    List<GrpcServiceDefinition> definitions = Lists.newArrayListWithCapacity(beanNames.size());
    Iterator beans = beanNames.iterator();

    while (beans.hasNext()) {
      String beanName = (String) beans.next();
      BindableService bindableService =
          this.applicationContext.getBean(beanName, BindableService.class);
      ServerServiceDefinition serviceDefinition = bindableService.bindService();
      definitions.add(
          new GrpcServiceDefinition(beanName, bindableService.getClass(), serviceDefinition));
    }
    return definitions;
  }
}

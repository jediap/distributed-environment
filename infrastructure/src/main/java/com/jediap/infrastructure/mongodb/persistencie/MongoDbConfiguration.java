package com.jediap.infrastructure.mongodb.persistencie;

import com.jediap.infrastructure.context.SpringContextParameters;
import com.mongodb.client.MongoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.Collection;
import java.util.Collections;

@Configuration
public class MongoDbConfiguration extends AbstractMongoClientConfiguration {

    private final SpringContextParameters parameter;

    public MongoDbConfiguration(SpringContextParameters parameter) {
        this.parameter = parameter;
    }

    @Override
    protected String getDatabaseName() {
        return "mdb_event_store";
    }

    @Override
    protected Collection<String> getMappingBasePackages() {
        return Collections.singleton("com.jediap");
    }

    @Bean
    public MongoClient mongoClient() {
        return new MongoDbClient().client(String.format(
                //"%s:%s@%s:%s/%s",
                "%s:%s/%s",
                // "", parameter.EVENT_STORE_USER
                // "", parameter.EVENT_STORE_PASS
                parameter.EVENT_STORE_HOST_DB,
                parameter.EVENT_STORE_PORT_DB,
                getDatabaseName()
        ),parameter.EVENT_STORE_AUTHENTICATION_DATABASE);
    }

    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongoClient(), getDatabaseName());
    }
}

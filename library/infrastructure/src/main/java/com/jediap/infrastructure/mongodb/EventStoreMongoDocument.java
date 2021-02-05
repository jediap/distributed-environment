package com.jediap.infrastructure.mongodb;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

@Document(collection = "event_store")
public class EventStoreMongoDocument {
    @Id
    private final String id;
    @Field("aggregate_id")
    private final String aggregateId;
    private final String name;
    //@Field(value = "occurred_on", targetType = FieldType.TIMESTAMP)
    private final String occurredOn;
    private final String version;
    @Indexed(unique = true)
    private final String body;
    private final String meta;

    public EventStoreMongoDocument(String id,
                                   String aggregateId,
                                   String name,
                                   String occurredOn,
                                   String version,
                                   String body,
                                   String meta) {
        this.id = id;
        this.aggregateId = aggregateId;
        this.name = name;
        this.occurredOn = occurredOn;
        this.version = version;
        this.body = body;
        this.meta = meta;
    }

    public String id() {
        return id;
    }

    public String aggregateId() {
        return aggregateId;
    }

    public String name() {
        return name;
    }

    public String occurredOn() {
        return occurredOn;
    }

    public String version() {
        return version;
    }

    public String body() {
        return body;
    }

    public String meta() {
        return meta;
    }
}

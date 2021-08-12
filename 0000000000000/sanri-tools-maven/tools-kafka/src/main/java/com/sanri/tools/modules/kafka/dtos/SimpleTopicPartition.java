package com.sanri.tools.modules.kafka.dtos;

import lombok.Data;
import lombok.Getter;

@Getter
public class SimpleTopicPartition {
    private String topic;
    private int partition;

    public SimpleTopicPartition() {
    }

    public SimpleTopicPartition(String topic, int partition) {
        this.topic = topic;
        this.partition = partition;
    }
}

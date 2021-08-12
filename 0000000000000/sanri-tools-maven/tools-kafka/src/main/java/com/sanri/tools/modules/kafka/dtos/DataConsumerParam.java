package com.sanri.tools.modules.kafka.dtos;

import lombok.Data;

@Data
public class DataConsumerParam {
    protected String clusterName;
    protected String topic;
    protected int partition;
    protected int perPartitionSize;
    protected String serializer;
    protected String classloaderName;
}

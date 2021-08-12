package com.sanri.tools.modules.core.dtos.param;

import lombok.Data;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;

@Data
public class KafkaConnectParam extends AbstractConnectParam{
    private KafkaProperties kafka;
    private String chroot = "/";        // kafka 在 zookeeper 上的数据路径,默认为 /
}

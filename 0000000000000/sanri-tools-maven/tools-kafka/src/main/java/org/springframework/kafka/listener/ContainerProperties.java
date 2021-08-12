package org.springframework.kafka.listener;

public class ContainerProperties {
    public static enum AckMode {
        RECORD,
        BATCH,
        TIME,
        COUNT,
        COUNT_TIME,
        MANUAL,
        MANUAL_IMMEDIATE;

        private AckMode() {
        }
    }
}

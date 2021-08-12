package org.springframework.kafka.security.jaas;

public class KafkaJaasLoginModuleInitializer {
    public static enum ControlFlag {
        REQUIRED,
        REQUISITE,
        SUFFICIENT,
        OPTIONAL;

        private ControlFlag() {
        }
    }
}

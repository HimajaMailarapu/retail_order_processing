package com.interview.retail_order_processing.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfig {

    @Value("${kafka.kafkaconnect:localhost:9092}")
    private String kafkaConnect;

    @Value("${kafka.autoOffsetReset:earliest}")
    private String autoOffsetReset;

    @Value("${kafka.ssl.truststore.location:test_path}")
    private String kafkaSSlTruststoreLocation;

    @Value("${kafka.ssl.truststore.password:password}")
    private String kafkaSSLTruststorePassword;

    @Value("${kafka.ssl.keystore.location:test_path}")
    private String kafkaSSLKeystoreLocation;

    @Value("${kafka.ssl.keystore.password:password}")
    private String kafkaSSLKeystorePassword;

    @Value("${kafka.security.protocol:SSL}")
    private String kafkaSecurityProtocol;

    @Value("${kafka.consumerCount:1}")
    private String consumerCount;

    @Value("${kafka.consumerStream:1}")
    private String consumerStream;

    @Value("${kafka.consumerRequestTimeoutMs:450000}")
    private String consumerRequestTimeoutMs;

    @Value("${kafka.sessionTimeoutMs:300000}")
    private String sessionTimeoutMs;

    public String retailOrderKafkaConfig() {
        String uri = "&autoOffsetReset=" + autoOffsetReset
                + "&securityProtocol=" + kafkaSecurityProtocol
                + "&sslTruststoreLocation=" + kafkaSSlTruststoreLocation
                + "&sslTruststorePassword=" + kafkaSSLTruststorePassword
                + "&sslKeystoreLocation=" + kafkaSSLKeystoreLocation
                + "&sslKeystorePassword=" + kafkaSSLKeystorePassword
                + "&consumersCount=" + consumerCount
                + "&consumerStreams=" + consumerStream
                + "&consumerRequestTimeoutMs=" + consumerRequestTimeoutMs
                + "&sessionTimeoutMs=" + sessionTimeoutMs
                + "&serializerClass=org.apache.kafka.common.serialization.StringSerializer";
        return uri;
    }
}

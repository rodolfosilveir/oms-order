package br.com.ambev.oms_order_manager.config;

import java.io.Serializable;
import java.util.HashMap;

import lombok.Generated;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
@Generated
public class ProducerKafkaConfig {

    private final KafkaProperties kafkaProperties;

    @Value("${spring.kafka.producer.topic.oms-order-stock-topic}")
    private String orderStockTopic;

    @Value("${spring.kafka.producer.topic.oms-order-notification-topic}")
    private String orderNotificationTopic;

    @Bean
    public KafkaAdmin.NewTopics topics() {
        return new KafkaAdmin.NewTopics(
                TopicBuilder.name(orderStockTopic).partitions(1).replicas(1).build(),
                TopicBuilder.name(orderNotificationTopic).partitions(1).replicas(1).build()
        );
    }

    @Bean
    public KafkaTemplate<String, Serializable> kafkaTemplate() {
        return new KafkaTemplate(jsonProducerFactory());
    }

    @Bean
    public ProducerFactory jsonProducerFactory() {
        var configs = new HashMap<String, Object>();
        configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
        configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(configs, new StringSerializer(), new JsonSerializer<>());
    }
    
}

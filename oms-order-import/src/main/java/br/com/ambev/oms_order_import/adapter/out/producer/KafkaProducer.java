package br.com.ambev.oms_order_import.adapter.out.producer;

import java.io.Serializable;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import br.com.ambev.oms_order_import.port.out.KafkaProducerPort;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class KafkaProducer implements KafkaProducerPort {
    
    private final KafkaTemplate<String, Serializable> kafkaTemplate;

    @Override
    public void sendEvent(String topic, Serializable message) {
        kafkaTemplate.send(topic, message);
    }
}

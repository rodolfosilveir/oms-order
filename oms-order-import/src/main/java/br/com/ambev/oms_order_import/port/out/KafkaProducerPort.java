package br.com.ambev.oms_order_import.port.out;

import java.io.Serializable;

public interface KafkaProducerPort {
    void sendEvent(String topic, Serializable message);
}

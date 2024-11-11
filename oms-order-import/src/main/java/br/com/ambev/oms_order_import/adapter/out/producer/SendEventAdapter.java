package br.com.ambev.oms_order_import.adapter.out.producer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import br.com.ambev.oms_order_import.adapter.out.producer.event.ImportOrderEvent;
import br.com.ambev.oms_order_import.domain.model.ImportOrder;
import br.com.ambev.oms_order_import.port.out.SendEventPort;
import br.com.ambev.oms_order_import.port.out.KafkaProducerPort;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SendEventAdapter implements SendEventPort {

    private final KafkaProducerPort kafkaProducerPort;

    @Value("${spring.kafka.producer.topic.oms-order-import-topic}")
    private String orderImportTopic;

    @Override
    public void sendImportOrder(ImportOrder event) {
        kafkaProducerPort.sendEvent(orderImportTopic, ImportOrderEvent.fromDomain(event));
    }
    
}

package br.com.ambev.oms_order_manager.adapter.out.producer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import br.com.ambev.oms_order_manager.adapter.in.consumer.event.ImportOrderEvent;
import br.com.ambev.oms_order_manager.adapter.out.producer.event.ImportOrderNotificationEvent;
import br.com.ambev.oms_order_manager.adapter.out.producer.event.OrderDetailsEvent;
import br.com.ambev.oms_order_manager.domain.model.OrderDetails;
import br.com.ambev.oms_order_manager.port.out.KafkaProducerPort;
import br.com.ambev.oms_order_manager.port.out.SendEventPort;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SendEventAdapter implements SendEventPort {

    private final KafkaProducerPort kafkaProducerPort;

    @Value("${spring.kafka.producer.topic.oms-order-stock-topic}")
    private String orderStockTopic;

    @Value("${spring.kafka.producer.topic.oms-order-notification-topic}")
    private String orderNotificationTopic;
    
    @Override
    public void sendOrderToStockEvent(OrderDetails event) {
        kafkaProducerPort.sendEvent(orderStockTopic, OrderDetailsEvent.fromDomain(event));
    }

    @Override
    public void sendOrderImportNotificationEvent(ImportOrderEvent event, String message) {
        kafkaProducerPort.sendEvent(orderNotificationTopic, ImportOrderNotificationEvent.fromDomain(message, event));
    }
    
}

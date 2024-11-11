package br.com.ambev.oms_order_import.adapter.in.consumer;

import java.util.Map;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;

import br.com.ambev.oms_order_import.adapter.out.producer.event.ImportOrderEvent;
import br.com.ambev.oms_order_import.port.in.OrderUC;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
@RequiredArgsConstructor
public class ImportOrderConsumer {

    private final OrderUC orderUC;

    @KafkaListener(
        topics = "${spring.kafka.consumer.topic.oms-order-import-topic}",
        groupId = "${spring.kafka.consumer.group-id}",
        containerFactory = "importOrderKafkaListenerContainerFactory"
    )
    public void importOrder(@Payload final ImportOrderEvent event, @Headers Map<String, Object> headers) throws JsonProcessingException {
        log.info("Order import event received. OrderId: %s".formatted(event.getOrderId()));
        orderUC.updateStatus(event.toDomain());
        log.info("Order import event finihed. OrderId: %s".formatted(event.getOrderId()));
    }
    
}

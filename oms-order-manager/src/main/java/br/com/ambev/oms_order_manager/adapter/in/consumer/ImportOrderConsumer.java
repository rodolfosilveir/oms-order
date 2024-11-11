package br.com.ambev.oms_order_manager.adapter.in.consumer;

import java.util.Map;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import br.com.ambev.oms_order_manager.adapter.in.consumer.event.ImportOrderEvent;
import br.com.ambev.oms_order_manager.port.in.OrderUC;
import br.com.ambev.oms_order_manager.port.out.SendEventPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
@RequiredArgsConstructor
public class ImportOrderConsumer {

    private final OrderUC orderUC;
    private final SendEventPort sendEventPort;

    @KafkaListener(
        topics = "${spring.kafka.consumer.topic.oms-order-import-topic}",
        groupId = "${spring.kafka.consumer.group-id}",
        containerFactory = "importOrderKafkaListenerContainerFactory"
    )
    public void importOrder(@Payload final ImportOrderEvent event, @Headers Map<String, Object> headers) {
        log.info("Order import event received. OrderId: %s".formatted(event.getOrderId()));
        try{
            orderUC.importOrder(event.toDomain());
            log.info("Order import event finihed. OrderId: %s".formatted(event.getOrderId()));
        }catch(Exception e){
            log.error("Error importing order. OrderId: %s".formatted(event.getOrderId()), e);
            sendEventPort.sendOrderImportNotificationEvent(event, e.getMessage());
        }
    }
    
}

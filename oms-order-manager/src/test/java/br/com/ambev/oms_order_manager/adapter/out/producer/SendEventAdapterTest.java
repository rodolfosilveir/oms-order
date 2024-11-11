package br.com.ambev.oms_order_manager.adapter.out.producer;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import br.com.ambev.oms_order_manager.adapter.in.consumer.event.ImportOrderEvent;
import br.com.ambev.oms_order_manager.domain.model.OrderDetails;
import br.com.ambev.oms_order_manager.port.out.KafkaProducerPort;

@ExtendWith(MockitoExtension.class)
class SendEventAdapterTest {

    @Mock
    private KafkaProducerPort kafkaProducerPort;

    @InjectMocks
    private SendEventAdapter sendEventAdapter;

    @Test
    @DisplayName("Deve enviar notificação, metodo sendOrderToStockEvent")
    void shoulSendOrderToStockEvent(){

        ReflectionTestUtils.setField(sendEventAdapter, "orderStockTopic", "topic");

        OrderDetails event = OrderDetails.builder().build();

        doNothing().when(kafkaProducerPort).sendEvent(anyString(), any());

        sendEventAdapter.sendOrderToStockEvent(event);

        verify(kafkaProducerPort, times(1)).sendEvent(anyString(), any());
    }

    @Test
    @DisplayName("Deve enviar notificação, metodo sendOrderImportNotificationEvent")
    void shoulSendOrderImportNotificationEvent(){

        ReflectionTestUtils.setField(sendEventAdapter, "orderNotificationTopic", "topic");

        ImportOrderEvent event = ImportOrderEvent.builder().build();

        doNothing().when(kafkaProducerPort).sendEvent(anyString(), any());

        sendEventAdapter.sendOrderImportNotificationEvent(event,  "message");

        verify(kafkaProducerPort, times(1)).sendEvent(anyString(), any());
    }
    
}

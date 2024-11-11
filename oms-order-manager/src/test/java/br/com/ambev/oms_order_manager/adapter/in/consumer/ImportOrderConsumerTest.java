package br.com.ambev.oms_order_manager.adapter.in.consumer;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.HashMap;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.ambev.oms_order_manager.adapter.in.consumer.event.ImportOrderEvent;
import br.com.ambev.oms_order_manager.adapter.in.consumer.event.ImportOrderEventMock;
import br.com.ambev.oms_order_manager.domain.model.OrderDetails;
import br.com.ambev.oms_order_manager.port.in.OrderUC;
import br.com.ambev.oms_order_manager.port.out.SendEventPort;

@ExtendWith(MockitoExtension.class)
class ImportOrderConsumerTest {

    @Mock
    private OrderUC orderUC;

    @Mock
    private SendEventPort sendEventPort;

    @InjectMocks
    private ImportOrderConsumer importOrderConsumer;

    @Test
    @DisplayName("Deve importar o pedido, metodo importOrder")
    void shouldImportOrder(){

        ImportOrderEvent event = ImportOrderEventMock.create();
        doNothing().when(orderUC).importOrder(any(OrderDetails.class));

        importOrderConsumer.importOrder(event, new HashMap<>());

        verify(orderUC, times(1)).importOrder(any(OrderDetails.class));
        verify(sendEventPort, never()).sendOrderImportNotificationEvent(any(), any());
        
    }

    @Test
    @DisplayName("Deve notificar o erro, metodo importOrder")
    void shouldNotificateErrorImportOrder(){

        ImportOrderEvent event = ImportOrderEventMock.create();
        Exception e = new RuntimeException("Test exception");
        doThrow(e).when(orderUC).importOrder(any(OrderDetails.class));
        doNothing().when(sendEventPort).sendOrderImportNotificationEvent(event, e.getMessage());

        importOrderConsumer.importOrder(event, new HashMap<>());

        verify(orderUC, times(1)).importOrder(any(OrderDetails.class));
        verify(sendEventPort, times(1)).sendOrderImportNotificationEvent(event, e.getMessage());
        
    }
    
}

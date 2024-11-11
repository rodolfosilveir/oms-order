package br.com.ambev.oms_order_import.adapter.in.consumer;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.HashMap;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fasterxml.jackson.core.JsonProcessingException;

import br.com.ambev.oms_order_import.adapter.out.producer.event.ImportOrderEventMock;
import br.com.ambev.oms_order_import.port.in.OrderUC;

@ExtendWith(MockitoExtension.class)
class ImportOrderConsumerTest {

    @Mock
    private OrderUC orderUC;

    @InjectMocks
    private ImportOrderConsumer importOrderConsumer;

    @Test
    @DisplayName("Deve importar o pedido, metodo importOrder")
    void shouldImportOrder() throws JsonProcessingException{

        doNothing().when(orderUC).updateStatus(any());

        importOrderConsumer.importOrder(ImportOrderEventMock.create(), new HashMap<>());

        verify(orderUC, times(1)).updateStatus(any());
        
    }
    
}

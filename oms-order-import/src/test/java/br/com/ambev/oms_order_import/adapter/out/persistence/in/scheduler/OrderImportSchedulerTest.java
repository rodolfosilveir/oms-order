package br.com.ambev.oms_order_import.adapter.out.persistence.in.scheduler;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fasterxml.jackson.core.JsonProcessingException;

import br.com.ambev.oms_order_import.adapter.in.scheduler.OrderImportScheduler;
import br.com.ambev.oms_order_import.port.in.OrderUC;

@ExtendWith(MockitoExtension.class)
class OrderImportSchedulerTest {

    @Mock
    private OrderUC orderUC;

    @InjectMocks
    private OrderImportScheduler orderImportScheduler;

    @Test
    @DisplayName("Deve retentar importar pedidos enviados, metodo retrySendedOrders")
    void shoulRetrySendedOrders() throws JsonProcessingException{

        doNothing().when(orderUC).retrySendedOrders();

        orderImportScheduler.retrySendedOrders();

        verify(orderUC, times(1)).retrySendedOrders();
    }
    
}

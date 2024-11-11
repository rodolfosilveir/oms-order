package br.com.ambev.oms_order_import.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fasterxml.jackson.core.JsonProcessingException;

import br.com.ambev.oms_order_import.domain.exception.OrderAlreadyImportedException;
import br.com.ambev.oms_order_import.domain.model.ImportOrder;
import br.com.ambev.oms_order_import.domain.model.ImportOrderMock;
import br.com.ambev.oms_order_import.port.out.ImportOrderPort;
import br.com.ambev.oms_order_import.port.out.SendEventPort;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private ImportOrderPort importOrderPort;

    @Mock
    private SendEventPort sendEventPort;

    @InjectMocks
    private OrderService orderService;

    @Test
    @DisplayName("Deve validar que o pedido ja existe, metodo importOrder")
    void shouldValidOrderAlreadyExistsImportOrder() throws JsonProcessingException{

        ImportOrder order = ImportOrderMock.create();

        when(importOrderPort.findByOrderId(order.getOrderId())).thenReturn(List.of(order));

        OrderAlreadyImportedException e = Assertions.assertThrows(OrderAlreadyImportedException.class, () -> {
            orderService.importOrder(order);
        });

        assertEquals("Order already imported: %s".formatted(order.getOrderId()), e.getMessage());
        verify(importOrderPort, times(1)).findByOrderId(order.getOrderId());
        verify(importOrderPort, never()).create(order);
        verify(sendEventPort, never()).sendImportOrder(order);
        verify(importOrderPort, never()).updateStatus(order);
        verify(importOrderPort, never()).findSendedOrders();
        verify(importOrderPort, never()).incrementAttempts(order);
    }

    @Test
    @DisplayName("Deve enviar o pedido sem erro, metodo importOrder")
    void shouldSendOrderImportOrder() throws JsonProcessingException{

        ImportOrder order = ImportOrderMock.create();

        when(importOrderPort.findByOrderId(order.getOrderId())).thenReturn(Collections.emptyList());
        doNothing().when(importOrderPort).create(order);
        doNothing().when(sendEventPort).sendImportOrder(order);

        orderService.importOrder(order);

        verify(importOrderPort, times(1)).findByOrderId(order.getOrderId());
        verify(importOrderPort, times(1)).create(order);
        verify(sendEventPort, times(1)).sendImportOrder(order);
        verify(importOrderPort, never()).updateStatus(order);
        verify(importOrderPort, never()).findSendedOrders();
        verify(importOrderPort, never()).incrementAttempts(order);
    }

    @Test
    @DisplayName("Deve enviar o pedido com erros, metodo importOrder")
    void shouldSendOrderWithErrorImportOrder() throws JsonProcessingException{

        ImportOrder order = ImportOrderMock.create();

        when(importOrderPort.findByOrderId(order.getOrderId())).thenReturn(Collections.emptyList());
        doThrow(new RuntimeException("Test exception")).when(importOrderPort).create(order);
        doThrow(new RuntimeException("Test exception")).when(sendEventPort).sendImportOrder(order);

        orderService.importOrder(order);

        verify(importOrderPort, times(1)).findByOrderId(order.getOrderId());
        verify(importOrderPort, times(1)).create(order);
        verify(sendEventPort, times(1)).sendImportOrder(order);
        verify(importOrderPort, never()).updateStatus(order);
        verify(importOrderPort, never()).findSendedOrders();
        verify(importOrderPort, never()).incrementAttempts(order);
    }

    @Test
    @DisplayName("Deve atualizar o pedido, metodo updateStatus")
    void shouldUpdateOrderUpdateStatus() throws JsonProcessingException{

        ImportOrder order = ImportOrderMock.create();

        doNothing().when(importOrderPort).updateStatus(order);

        orderService.updateStatus(order);

        verify(importOrderPort, never()).findByOrderId(order.getOrderId());
        verify(importOrderPort, never()).create(order);
        verify(sendEventPort, never()).sendImportOrder(order);
        verify(importOrderPort, times(1)).updateStatus(order);
        verify(importOrderPort, never()).findSendedOrders();
        verify(importOrderPort, never()).incrementAttempts(order);
    }

    @Test
    @DisplayName("Deve retentar enviar o pedido sem erro, metodo retrySendedOrders")
    void shouldRetrySendOrdersRetrySendedOrders() throws JsonProcessingException{

        ImportOrder order = ImportOrderMock.create();

        when(importOrderPort.findSendedOrders()).thenReturn(List.of(order));
        doNothing().when(importOrderPort).incrementAttempts(order);
        doNothing().when(sendEventPort).sendImportOrder(order);

        orderService.retrySendedOrders();

        verify(importOrderPort, never()).findByOrderId(order.getOrderId());
        verify(importOrderPort, never()).create(order);
        verify(sendEventPort, times(1)).sendImportOrder(order);
        verify(importOrderPort, never()).updateStatus(order);
        verify(importOrderPort, times(1)).findSendedOrders();
        verify(importOrderPort, times(1)).incrementAttempts(order);
    }

    @Test
    @DisplayName("Deve retentar enviar o pedido com erro, metodo retrySendedOrders")
    void shouldRetrySendOrdersWithErrorRetrySendedOrders() throws JsonProcessingException{

        ImportOrder order = ImportOrderMock.create();

        when(importOrderPort.findSendedOrders()).thenReturn(List.of(order));
        doNothing().when(importOrderPort).incrementAttempts(order);
        doThrow(new RuntimeException("Test exception")).when(sendEventPort).sendImportOrder(order);

        orderService.retrySendedOrders();

        verify(importOrderPort, never()).findByOrderId(order.getOrderId());
        verify(importOrderPort, never()).create(order);
        verify(sendEventPort, times(1)).sendImportOrder(order);
        verify(importOrderPort, never()).updateStatus(order);
        verify(importOrderPort, times(1)).findSendedOrders();
        verify(importOrderPort, times(1)).incrementAttempts(order);
    }
    
}

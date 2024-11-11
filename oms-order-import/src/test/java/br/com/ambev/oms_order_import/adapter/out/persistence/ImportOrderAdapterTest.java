package br.com.ambev.oms_order_import.adapter.out.persistence;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fasterxml.jackson.core.JsonProcessingException;

import br.com.ambev.oms_order_import.adapter.out.persistence.entity.ImportOrderEntity;
import br.com.ambev.oms_order_import.adapter.out.persistence.entity.ImportOrderEntityMock;
import br.com.ambev.oms_order_import.adapter.out.persistence.repository.ImportOrderRepository;
import br.com.ambev.oms_order_import.domain.model.ImportOrder;
import br.com.ambev.oms_order_import.domain.model.ImportOrderMock;

@ExtendWith(MockitoExtension.class)
class ImportOrderAdapterTest {

    @Mock
    private ImportOrderRepository importOrderRepository;

    @InjectMocks
    private ImportOrderAdapter importOrderAdapter;

    @Test
    @DisplayName("Deve criar o pedido, metodo create")
    void shouldCreateOrderCreate() throws JsonProcessingException{

        ImportOrder order = ImportOrderMock.create();

        when(importOrderRepository.saveAndFlush(any(ImportOrderEntity.class))).thenReturn(ImportOrderEntityMock.create());

        importOrderAdapter.create(order);

        verify(importOrderRepository, times(1)).saveAndFlush(any(ImportOrderEntity.class));
        verify(importOrderRepository, never()).findByOrderId(anyString());
        verify(importOrderRepository, never()).updateOrderStatus(any(), any());
        verify(importOrderRepository, never()).findSendedOrders();
        verify(importOrderRepository, never()).findAttemptByOrderId(any());
    }

    @Test
    @DisplayName("Deve achar o pedido, metodo findByOrderId")
    void shouldFindOrderFindByOrderId(){

        String orderId = "1";

        when(importOrderRepository.findByOrderId(orderId)).thenReturn(List.of(ImportOrderEntityMock.create()));

        importOrderAdapter.findByOrderId(orderId);

        verify(importOrderRepository, never()).saveAndFlush(any(ImportOrderEntity.class));
        verify(importOrderRepository, times(1)).findByOrderId(orderId);
        verify(importOrderRepository, never()).updateOrderStatus(any(), any());
        verify(importOrderRepository, never()).findSendedOrders();
        verify(importOrderRepository, never()).findAttemptByOrderId(any());
    }

    @Test
    @DisplayName("Deve atualizar status do pedido, metodo updateStatus")
    void shouldUpdateOrderStatus(){

        ImportOrder order = ImportOrderMock.create();

        doNothing().when(importOrderRepository).updateOrderStatus(order.getOrderId(), order.getStatus().toString());

        importOrderAdapter.updateStatus(order);

        verify(importOrderRepository, never()).saveAndFlush(any(ImportOrderEntity.class));
        verify(importOrderRepository, never()).findByOrderId(anyString());
        verify(importOrderRepository, times(1)).updateOrderStatus(order.getOrderId(), order.getStatus().toString());
        verify(importOrderRepository, never()).findSendedOrders();
        verify(importOrderRepository, never()).findAttemptByOrderId(any());
    }

    
    @Test
    @DisplayName("Deve achar o pedido enviados, metodo findSendedOrders")
    void shouldFindSendedOrderFindSendedOrders(){

        when(importOrderRepository.findSendedOrders()).thenReturn(List.of(ImportOrderEntityMock.create()));

        importOrderAdapter.findSendedOrders();

        verify(importOrderRepository, never()).saveAndFlush(any(ImportOrderEntity.class));
        verify(importOrderRepository, never()).findByOrderId(any());
        verify(importOrderRepository, never()).updateOrderStatus(any(), any());
        verify(importOrderRepository, times(1)).findSendedOrders();
        verify(importOrderRepository, never()).findAttemptByOrderId(any());
    }
    
    @Test
    @DisplayName("Deve aumentar as tentativas do pedido, metodo incrementAttempts")
    void shouldIncrementOrderAttempts() throws JsonProcessingException{

        ImportOrder order = ImportOrderMock.create();

        when(importOrderRepository.findAttemptByOrderId(order.getOrderId())).thenReturn(Optional.of(1));
        when(importOrderRepository.saveAndFlush(any(ImportOrderEntity.class))).thenReturn(ImportOrderEntityMock.create());

        importOrderAdapter.incrementAttempts(order);

        verify(importOrderRepository, times(1)).saveAndFlush(any(ImportOrderEntity.class));
        verify(importOrderRepository, never()).findByOrderId(anyString());
        verify(importOrderRepository, never()).updateOrderStatus(order.getOrderId(), order.getStatus().toString());
        verify(importOrderRepository, never()).findSendedOrders();
        verify(importOrderRepository, times(1)).findAttemptByOrderId(order.getOrderId());
    }

    @Test
    @DisplayName("Deve aumentar as tentativas do primeiro pedido, metodo incrementAttempts")
    void shouldFirstOrderIncrementOrderAttempts() throws JsonProcessingException{

        ImportOrder order = ImportOrderMock.create();

        when(importOrderRepository.findAttemptByOrderId(order.getOrderId())).thenReturn(Optional.empty());
        when(importOrderRepository.saveAndFlush(any(ImportOrderEntity.class))).thenReturn(ImportOrderEntityMock.create());

        importOrderAdapter.incrementAttempts(order);

        verify(importOrderRepository, times(1)).saveAndFlush(any(ImportOrderEntity.class));
        verify(importOrderRepository, never()).findByOrderId(anyString());
        verify(importOrderRepository, never()).updateOrderStatus(order.getOrderId(), order.getStatus().toString());
        verify(importOrderRepository, never()).findSendedOrders();
        verify(importOrderRepository, times(1)).findAttemptByOrderId(order.getOrderId());
    }
}

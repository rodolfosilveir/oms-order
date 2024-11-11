package br.com.ambev.oms_order_manager.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.ambev.oms_order_manager.domain.exception.OrderNotFoundException;
import br.com.ambev.oms_order_manager.domain.model.ItemMock;
import br.com.ambev.oms_order_manager.domain.model.OrderDetails;
import br.com.ambev.oms_order_manager.domain.model.OrderDetailsMock;
import br.com.ambev.oms_order_manager.port.out.OrderDetailsPort;
import br.com.ambev.oms_order_manager.port.out.OrderItemPort;
import br.com.ambev.oms_order_manager.port.out.SendEventPort;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private OrderDetailsPort orderDetailsPort;

    @Mock
    private OrderItemPort orderItemPort;

    @Mock
    private SendEventPort sendEventPort;

    @InjectMocks
    private OrderService orderService;

    @Test
    @DisplayName("Deve lançar exceção pedido não encontrado, metodo findOrderById")
    void shouldNotFindOrder(){

        String id = "1";
        when(orderDetailsPort.findOrderById(id)).thenReturn(Optional.empty());

        OrderNotFoundException e = Assertions.assertThrows(OrderNotFoundException.class, () -> {
            orderService.findOrderById(id);
        });

        Assertions.assertEquals("Order not found with id: 1", e.getMessage());
        verify(orderDetailsPort, times(1)).findOrderById(id);
        verify(orderItemPort, never()).findItemsByOrderIdWithQuantity(id);
    }

    @Test
    @DisplayName("Deve encontrar o pedido, metodo findOrderById")
    void shouldFindOrder(){

        String id = "1";
        when(orderDetailsPort.findOrderById(id)).thenReturn(Optional.of(OrderDetailsMock.create(id)));
        when(orderItemPort.findItemsByOrderIdWithQuantity(id)).thenReturn(List.of(ItemMock.create()));

        OrderDetails details = orderService.findOrderById(id);

        assertEquals(id, details.getId());
        verify(orderDetailsPort, times(1)).findOrderById(id);
        verify(orderItemPort, times(1)).findItemsByOrderIdWithQuantity(id);
        verify(orderDetailsPort, never()).saveOrder(any());
        verify(orderItemPort, never()).saveItems(any(), any());
        verify(sendEventPort, never()).sendOrderToStockEvent(any());
    }

    @Test
    @DisplayName("Deve importar o pedido, metodo importOrder")
    void shouldImportOrder(){

        String id = "1";
        OrderDetails orderDetails = OrderDetailsMock.create(id);

        doNothing().when(orderDetailsPort).saveOrder(orderDetails);
        doNothing().when(orderItemPort).saveItems(orderDetails.getId(), orderDetails.getOrderItems());
        doNothing().when(sendEventPort).sendOrderToStockEvent(orderDetails);

        orderService.importOrder(orderDetails);

        verify(orderDetailsPort, never()).findOrderById(id);
        verify(orderItemPort, never()).findItemsByOrderIdWithQuantity(id);
        verify(orderDetailsPort, times(1)).saveOrder(orderDetails);
        verify(orderItemPort, times(1)).saveItems(orderDetails.getId(), orderDetails.getOrderItems());
        verify(sendEventPort, times(1)).sendOrderToStockEvent(orderDetails);
    }
    
}

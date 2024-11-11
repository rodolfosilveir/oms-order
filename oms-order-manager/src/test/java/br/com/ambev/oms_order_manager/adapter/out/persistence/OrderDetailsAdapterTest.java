package br.com.ambev.oms_order_manager.adapter.out.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.ambev.oms_order_manager.adapter.out.persistence.entity.OrderDetailsEntity;
import br.com.ambev.oms_order_manager.adapter.out.persistence.repository.OrderDetailsRepository;
import br.com.ambev.oms_order_manager.domain.model.OrderDetails;
import br.com.ambev.oms_order_manager.domain.model.OrderDetailsMock;

@ExtendWith(MockitoExtension.class)
class OrderDetailsAdapterTest {

    @Mock
    private OrderDetailsRepository orderDetailsRepository;

    @InjectMocks
    private OrderDetailsAdapter orderDetailsAdapter;

    @Test
    @DisplayName("Deve achar o pedido, metodo findOrderById")
    void shouldFindOrderById(){

        String id = "1";

        when(orderDetailsRepository.findById(id)).thenReturn(Optional.of(OrderDetailsEntity.fromDomain(OrderDetailsMock.create(id))));
        Optional<OrderDetails> details = orderDetailsAdapter.findOrderById(id);

        assertTrue(details.isPresent());
        assertEquals(id, details.get().getId());
        verify(orderDetailsRepository, times(1)).findById(id);
        verify(orderDetailsRepository, never()).save(any());

    }

    @Test
    @DisplayName("Deve salvar o pedido, metodo saveOrder")
    void shouldSaveOrder(){

        String id = "1";
        OrderDetails orderDetails = OrderDetailsMock.create(id);
        when(orderDetailsRepository.save(any())).thenReturn(OrderDetailsEntity.fromDomain(orderDetails));
        orderDetailsAdapter.saveOrder(orderDetails);

        verify(orderDetailsRepository, never()).findById(id);
        verify(orderDetailsRepository, times(1)).save(any());

    }
    
}

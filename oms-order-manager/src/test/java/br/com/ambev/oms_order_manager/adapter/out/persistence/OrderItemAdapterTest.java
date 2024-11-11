package br.com.ambev.oms_order_manager.adapter.out.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.ambev.oms_order_manager.adapter.out.persistence.entity.OrderItemProjectionMock;
import br.com.ambev.oms_order_manager.adapter.out.persistence.repository.OrderItemRepository;
import br.com.ambev.oms_order_manager.domain.model.Item;
import br.com.ambev.oms_order_manager.domain.model.OrderItem;

@ExtendWith(MockitoExtension.class)
class OrderItemAdapterTest {

    @Mock
    private OrderItemRepository orderItemRepository;

    @InjectMocks
    private OrderItemAdapter orderItemAdapter;

    @Test
    @DisplayName("Deve achar os itens pedido, metodo findItemsByOrderIdWithQuantity")
    void shouldFindItemsByOrderIdWithQuantity(){

        String id = "1";

        when(orderItemRepository.findItemsByOrderIdWithQuantity(id)).thenReturn(List.of(OrderItemProjectionMock.create()));
        List<Item> itens = orderItemAdapter.findItemsByOrderIdWithQuantity(id);

        assertEquals(1, itens.size());
        verify(orderItemRepository, times(1)).findItemsByOrderIdWithQuantity(id);
        verify(orderItemRepository, never()).saveAll(anyList());
    }

    @Test
    @DisplayName("Deve salvar os itens pedido, metodo saveItems")
    void shouldSaveItems(){

        when(orderItemRepository.saveAll(anyList())).thenReturn(null);

        orderItemAdapter.saveItems("1", List.of(OrderItem.builder().id(1).quantity(1).build()));
        verify(orderItemRepository, never()).findItemsByOrderIdWithQuantity(anyString());
        verify(orderItemRepository, times(1)).saveAll(anyList());
    }
    
}

package br.com.ambev.oms_order_manager.port.out;

import java.util.List;

import br.com.ambev.oms_order_manager.domain.model.OrderItem;
import br.com.ambev.oms_order_manager.domain.model.Item;

public interface OrderItemPort {
        
        List<Item> findItemsByOrderIdWithQuantity(String orderId);

        void saveItems(String orderId, List<OrderItem> orderItems);
}

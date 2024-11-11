package br.com.ambev.oms_order_manager.port.out;

import java.util.Optional;

import br.com.ambev.oms_order_manager.domain.model.OrderDetails;

public interface OrderDetailsPort {
    
    Optional<OrderDetails> findOrderById(String id);

    void saveOrder(OrderDetails order);
}

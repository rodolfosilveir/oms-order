package br.com.ambev.oms_order_manager.port.in;

import br.com.ambev.oms_order_manager.domain.model.OrderDetails;

public interface OrderUC {
    
    OrderDetails findOrderById(String id);

    void importOrder(OrderDetails order);
}

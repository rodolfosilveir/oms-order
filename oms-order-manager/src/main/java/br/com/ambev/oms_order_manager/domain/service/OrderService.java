package br.com.ambev.oms_order_manager.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ambev.oms_order_manager.domain.exception.OrderNotFoundException;
import br.com.ambev.oms_order_manager.domain.model.OrderDetails;
import br.com.ambev.oms_order_manager.port.in.OrderUC;
import br.com.ambev.oms_order_manager.port.out.OrderDetailsPort;
import br.com.ambev.oms_order_manager.port.out.OrderItemPort;
import br.com.ambev.oms_order_manager.port.out.SendEventPort;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService implements OrderUC {

    private final OrderDetailsPort orderDetailsPort;
    private final OrderItemPort orderItemPort;
    private final SendEventPort sendEventPort;

    @Override
    public OrderDetails findOrderById(String id) {
        OrderDetails order = orderDetailsPort.findOrderById(id).orElseThrow(() -> new OrderNotFoundException(id));
        order.setItems(orderItemPort.findItemsByOrderIdWithQuantity(id));
        return order;
    }

    @Override
    @Transactional
    public void importOrder(OrderDetails order) {
        order.updateStatus("processing");
        orderDetailsPort.saveOrder(order);
        orderItemPort.saveItems(order.getId(), order.getOrderItems());
        sendEventPort.sendOrderToStockEvent(order);
    }
    
}

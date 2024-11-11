package br.com.ambev.oms_order_manager.port.out;

import br.com.ambev.oms_order_manager.adapter.in.consumer.event.ImportOrderEvent;
import br.com.ambev.oms_order_manager.domain.model.OrderDetails;

public interface SendEventPort {
        
        void sendOrderToStockEvent(OrderDetails event);
        void sendOrderImportNotificationEvent(ImportOrderEvent event, String message);
}

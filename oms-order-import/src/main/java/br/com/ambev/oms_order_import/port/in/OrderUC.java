package br.com.ambev.oms_order_import.port.in;

import com.fasterxml.jackson.core.JsonProcessingException;

import br.com.ambev.oms_order_import.domain.model.ImportOrder;

public interface OrderUC {

    void importOrder(ImportOrder importOrder) throws JsonProcessingException;
    void updateStatus(ImportOrder importOrder)  throws JsonProcessingException;
    void retrySendedOrders() throws JsonProcessingException;
    
}

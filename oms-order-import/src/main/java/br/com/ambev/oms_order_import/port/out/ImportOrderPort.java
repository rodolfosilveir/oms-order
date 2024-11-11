package br.com.ambev.oms_order_import.port.out;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;

import br.com.ambev.oms_order_import.domain.model.ImportOrder;

public interface ImportOrderPort {
    
    void create(ImportOrder importOrder) throws JsonProcessingException;

    List<ImportOrder> findByOrderId(String orderId);

    void updateStatus(ImportOrder importOrder);

    List<ImportOrder> findSendedOrders();

    void incrementAttempts(ImportOrder importOrder) throws JsonProcessingException;
}

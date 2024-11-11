package br.com.ambev.oms_order_import.domain.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;

import br.com.ambev.oms_order_import.domain.exception.OrderAlreadyImportedException;
import br.com.ambev.oms_order_import.domain.model.ImportOrder;
import br.com.ambev.oms_order_import.domain.model.ImportOrderStatus;
import br.com.ambev.oms_order_import.port.in.OrderUC;
import br.com.ambev.oms_order_import.port.out.SendEventPort;
import br.com.ambev.oms_order_import.port.out.ImportOrderPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@RequiredArgsConstructor
public class OrderService implements OrderUC {

    private final ImportOrderPort importOrderPort;
    private final SendEventPort sendEventPort;
    
    @Override
    public void importOrder(ImportOrder importOrder) throws JsonProcessingException {
        LocalDateTime occurrenceDate = LocalDateTime.now();

        if(!importOrderPort.findByOrderId(importOrder.getOrderId()).isEmpty()) {
            log.info("Order already imported: {}", importOrder.getOrderId());
            throw new OrderAlreadyImportedException(importOrder.getOrderId());
        }
        try{
            importOrder.updateOccurrenceDate(occurrenceDate);
            importOrder.updateStatus(ImportOrderStatus.SENDED);
            importOrderPort.create(importOrder);
        } catch (Exception e) {
            log.error("Error saving order", e);
        }

        try{
            sendEventPort.sendImportOrder(importOrder);
        } catch (Exception e) {
            log.error("The order will be automatically reprocessed shortly. ", e);
        }

    }

    @Override
    public void updateStatus(ImportOrder importOrder){
        importOrderPort.updateStatus(importOrder.updateStatus(ImportOrderStatus.IMPORTED));
        log.info("Order imported successfuly: {}", importOrder.getOrderId());
    }

    @Override
    public void retrySendedOrders() throws JsonProcessingException {

        List<ImportOrder> sendedOrders = importOrderPort.findSendedOrders();

        for (ImportOrder importOrder : sendedOrders) {
            try {
                importOrderPort.incrementAttempts(importOrder);
                sendEventPort.sendImportOrder(importOrder);
            } catch (Exception e) {
                log.error("Error retrying order", e);
            }
        }
    }

    
}

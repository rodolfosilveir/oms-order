package br.com.ambev.oms_order_import.domain.model;

import java.time.LocalDateTime;
import java.util.Arrays;

public class ImportOrderMock {

    public static ImportOrder create(){
        return ImportOrder.builder()
                .orderId("123")
                .occurrenceDate(LocalDateTime.now())
                .store("store")
                .status(ImportOrderStatus.SENDED)
                .originSystem("originSystem")
                .pickup("pickup")
                .items(Arrays.asList(OrderItemMock.create()))
                .build();
    }
    
}

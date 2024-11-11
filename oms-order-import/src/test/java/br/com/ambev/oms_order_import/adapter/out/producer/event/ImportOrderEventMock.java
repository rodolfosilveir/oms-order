package br.com.ambev.oms_order_import.adapter.out.producer.event;

import java.time.LocalDateTime;
import java.util.Collections;

public class ImportOrderEventMock {
    
    public static ImportOrderEvent create() {
        return ImportOrderEvent.builder()
                .orderId("123")
                .occurrenceDate(LocalDateTime.now())
                .store("store")
                .status("status")
                .originSystem("originSystem")
                .pickup("pickup")
                .items(Collections.emptyList())
                .build();
    }
}

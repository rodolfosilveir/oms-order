package br.com.ambev.oms_order_manager.adapter.in.consumer.event;

import java.time.LocalDateTime;
import java.util.List;

public class ImportOrderEventMock {

    public static ImportOrderEvent create() {
        return ImportOrderEvent.builder()
            .orderId("1")
            .occurrenceDate(LocalDateTime.now())
            .store("store")
            .status("status")
            .originSystem("originSystem")
            .pickup("pickup")
            .items(List.of(ItemEvent.builder().id(1).quantity(1).build()))
            .build();
    }
    
}

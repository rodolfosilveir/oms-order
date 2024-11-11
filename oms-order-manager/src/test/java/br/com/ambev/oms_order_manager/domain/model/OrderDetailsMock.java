package br.com.ambev.oms_order_manager.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;

public class OrderDetailsMock {

    public static OrderDetails create(String id) {
        return OrderDetails.builder()
                .id(id)
                .occurrenceDate(LocalDateTime.now())
                .store("store")
                .status("status")
                .originSystem("originSystem")
                .pickup("pickup")
                .items(Arrays.asList(Item.builder()
                        .id(1)
                        .name("item")
                        .price(BigDecimal.TEN)
                        .quantity(1)
                        .volumeMililiters(200)
                        .build()))
                .orderItems(Arrays.asList(OrderItem.builder()
                        .id(2)
                        .quantity(1)
                        .build()))
                .build();
    }	
    
}

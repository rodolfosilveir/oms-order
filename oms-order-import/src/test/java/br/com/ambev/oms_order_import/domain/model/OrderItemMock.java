package br.com.ambev.oms_order_import.domain.model;

public class OrderItemMock {
    
    public static OrderItem create(){
        return OrderItem.builder()
                .id(1)
                .quantity(1)
                .build();
    }
}

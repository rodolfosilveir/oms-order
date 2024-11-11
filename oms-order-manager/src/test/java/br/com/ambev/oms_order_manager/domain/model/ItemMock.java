package br.com.ambev.oms_order_manager.domain.model;

import java.math.BigDecimal;

public class ItemMock {

    public static Item create() {
        return Item.builder()
                .id(1)
                .name("item")
                .description("description")
                .category("category")
                .volumeMililiters(1000)
                .price(BigDecimal.TEN)
                .quantity(1)
                .build();
    }
    
}

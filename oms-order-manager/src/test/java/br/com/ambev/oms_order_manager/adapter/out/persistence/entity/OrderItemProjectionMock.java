package br.com.ambev.oms_order_manager.adapter.out.persistence.entity;

import java.math.BigDecimal;

public class OrderItemProjectionMock implements OrderItemProjection {

    private Integer itemId;
    private String mame;
    private String description;
    private String category;
    private Integer volumeMililiters;
    private BigDecimal price;
    private Integer quantity;

    @Override
    public Integer getItemId() {
        return itemId;
    }

    @Override
    public String getName() {
        return mame;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getCategory() {
        return category;
    }

    @Override
    public Integer getVolumeMililiters() {
        return volumeMililiters;
    }

    @Override
    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public Integer getQuantity() {
        return quantity;
    }

    public static OrderItemProjectionMock create(){
        OrderItemProjectionMock orderItemProjectionMock = new OrderItemProjectionMock();
        orderItemProjectionMock.itemId = 1;
        orderItemProjectionMock.mame = "Cerveja";
        orderItemProjectionMock.description = "Cerveja Pilsen";
        orderItemProjectionMock.category = "Alcoolica";
        orderItemProjectionMock.volumeMililiters = 350;
        orderItemProjectionMock.price = BigDecimal.valueOf(2.5);
        orderItemProjectionMock.quantity = 10;

        return orderItemProjectionMock;
    }
    
}

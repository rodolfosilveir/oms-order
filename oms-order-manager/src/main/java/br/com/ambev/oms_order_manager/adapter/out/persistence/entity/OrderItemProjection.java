package br.com.ambev.oms_order_manager.adapter.out.persistence.entity;

import java.math.BigDecimal;

public interface OrderItemProjection {
    Integer getItemId();
    String getName();
    String getDescription();
    String getCategory();
    Integer getVolumeMililiters();
    BigDecimal getPrice();
    Integer getQuantity();
}
package br.com.ambev.oms_order_manager.domain.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class OrderItem {
    private Integer id;
    private Integer quantity;
}

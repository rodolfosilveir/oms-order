package br.com.ambev.oms_order_manager.adapter.out.persistence.entity;

import br.com.ambev.oms_order_manager.domain.model.OrderItem;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "order_item")
@Builder(access = AccessLevel.PRIVATE)
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemEntity {

    @EmbeddedId
    private OrderItemId id;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    public static OrderItemEntity fromDomain(String orderId, OrderItem orderItem) {
        return OrderItemEntity.builder()
                .id(new OrderItemId(orderId, orderItem.getId()))
                .quantity(orderItem.getQuantity())
                .build();
    }
}

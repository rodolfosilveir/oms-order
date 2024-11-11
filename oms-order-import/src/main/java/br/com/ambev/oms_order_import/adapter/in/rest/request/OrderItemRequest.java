package br.com.ambev.oms_order_import.adapter.in.rest.request;

import br.com.ambev.oms_order_import.domain.model.OrderItem;
import lombok.*;

@Generated
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemRequest {
    
    private Integer id;
    private Integer quantity;

    public OrderItem toDomain() {
        return OrderItem.builder()
            .id(this.getId())
            .quantity(this.getQuantity())
            .build();
    }
    
}

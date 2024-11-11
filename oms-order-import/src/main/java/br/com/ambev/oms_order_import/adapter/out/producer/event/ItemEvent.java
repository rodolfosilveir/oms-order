package br.com.ambev.oms_order_import.adapter.out.producer.event;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.ambev.oms_order_import.domain.model.OrderItem;
import lombok.*;

@Generated
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemEvent implements Serializable{

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("quantity")
    private Integer quantity;

    public static ItemEvent fromDomain(OrderItem item) {
        return ItemEvent.builder()
                .id(item.getId())
                .quantity(item.getQuantity())
                .build();
    }

    public OrderItem toDomain(){
        return OrderItem.builder()
                .id(this.id)
                .quantity(this.quantity)
                .build();
    }
    
}

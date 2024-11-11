package br.com.ambev.oms_order_manager.adapter.in.consumer.event;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.ambev.oms_order_manager.domain.model.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemEvent implements Serializable{

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("quantity")
    private Integer quantity;
    
    public OrderItem toDomain() {
        return OrderItem.builder()
            .id(this.getId())
            .quantity(this.getQuantity())
            .build();
    }
}

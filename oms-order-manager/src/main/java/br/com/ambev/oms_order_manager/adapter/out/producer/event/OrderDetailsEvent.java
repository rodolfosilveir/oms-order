package br.com.ambev.oms_order_manager.adapter.out.producer.event;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.ambev.oms_order_manager.domain.model.OrderDetails;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailsEvent implements Serializable{

    @JsonProperty("order_id")
    private String orderId;

    @JsonProperty("occurrence_date")
    private LocalDateTime occurrenceDate;

    @JsonProperty("store")
    private String store;

    @JsonProperty("status")
    private String status;

    @JsonProperty("origin_system")
    private String originSystem;

    @JsonProperty("pickup")
    private String pickup;

    public static OrderDetailsEvent fromDomain(OrderDetails orderDetails) {
        return OrderDetailsEvent.builder()
            .orderId(orderDetails.getId())
            .occurrenceDate(orderDetails.getOccurrenceDate())
            .store(orderDetails.getStore())
            .status(orderDetails.getStatus())
            .originSystem(orderDetails.getOriginSystem())
            .pickup(orderDetails.getPickup())
            .build();
    }
    
}

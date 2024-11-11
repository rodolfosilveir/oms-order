package br.com.ambev.oms_order_manager.adapter.in.consumer.event;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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
public class ImportOrderEvent implements Serializable{

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

    @JsonProperty("items")
    private List<ItemEvent> items;

    public OrderDetails toDomain() {
        return OrderDetails.builder()
            .id(this.getOrderId())
            .occurrenceDate(this.getOccurrenceDate())
            .store(this.getStore())
            .status(this.getStatus())
            .originSystem(this.getOriginSystem())
            .pickup(this.getPickup())
            .orderItems(this.getItems().stream().map(ItemEvent::toDomain).collect(Collectors.toList()))
            .build();
    }

}

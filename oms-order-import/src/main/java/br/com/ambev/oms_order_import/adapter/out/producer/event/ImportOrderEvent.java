package br.com.ambev.oms_order_import.adapter.out.producer.event;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.ambev.oms_order_import.domain.model.ImportOrder;
import br.com.ambev.oms_order_import.domain.model.ImportOrderStatus;
import lombok.*;

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

    public static ImportOrderEvent fromDomain(ImportOrder order) {
        return ImportOrderEvent.builder()
                .orderId(order.getOrderId())
                .occurrenceDate(order.getOccurrenceDate())
                .store(order.getStore())
                .status(order.getStatus().toString())
                .originSystem(order.getOriginSystem())
                .pickup(order.getPickup())
                .items(order.getItems().stream().map(ItemEvent::fromDomain).collect(Collectors.toList()))
                .build();
    }

    public ImportOrder toDomain(){
        return ImportOrder.builder()
                .orderId(this.orderId)
                .store(this.store)
                .status(ImportOrderStatus.fromText(this.status))
                .originSystem(this.originSystem)
                .pickup(this.pickup)
                .items(this.items.stream().map(ItemEvent::toDomain).collect(Collectors.toList()))
                .build();
    }
    
}

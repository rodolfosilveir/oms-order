package br.com.ambev.oms_order_import.adapter.in.rest.request;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.ambev.oms_order_import.domain.model.ImportOrder;
import br.com.ambev.oms_order_import.domain.model.ImportOrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ImportOrderRequest {

    @JsonProperty("order-id")
    private String orderId;

    @JsonProperty("store")
    private String store;

    @JsonProperty("status")
    private String status;

    @JsonProperty("origin_system")
    private String originSystem;

    @JsonProperty("pickup")
    private String pickup;

    @JsonProperty("items")
    private List<OrderItemRequest> items;

    public ImportOrder toDomain(){
        return ImportOrder.builder()
            .orderId(orderId)
            .store(store)
            .status(ImportOrderStatus.fromText(status))
            .originSystem(originSystem)
            .pickup(pickup)
            .items(items.stream().map(OrderItemRequest::toDomain).toList())
            .build();
    }
    
}

package br.com.ambev.oms_order_manager.adapter.in.rest.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import br.com.ambev.oms_order_manager.domain.model.OrderDetails;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder(access = AccessLevel.PRIVATE)
public class OrderResponse {

    private String id;

    private LocalDateTime occurrenceDate;

    private String store;

    private String originSystem;

    private String status;

    private String pickup;

    private BigDecimal totalValue;

    private List<ItemResponse> items;

    public static OrderResponse createFromDomain(OrderDetails order) {
        return OrderResponse.builder()
            .id(order.getId())
            .occurrenceDate(order.getOccurrenceDate())
            .store(order.getStore())
            .originSystem(order.getOriginSystem())
            .status(order.getStatus())
            .pickup(order.getPickup())
            .totalValue(order.getTotalValue())
            .items(ItemResponse.fromDomain(order.getItems()))
            .build();
    }
    
}

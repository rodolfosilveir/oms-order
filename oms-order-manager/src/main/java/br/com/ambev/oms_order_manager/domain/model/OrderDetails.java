package br.com.ambev.oms_order_manager.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter(value = AccessLevel.PRIVATE)
public class OrderDetails {

    private String id;

    private LocalDateTime occurrenceDate;

    private String store;

    private String status;

    private String originSystem;

    private String pickup;

    @Setter
    private List<Item> items;

    private List<OrderItem> orderItems;

    public BigDecimal getTotalValue() {
        return items.stream()
                .map(item -> item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void updateStatus(String status) {
        this.status = status;
    }
    
}

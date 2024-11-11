package br.com.ambev.oms_order_import.domain.model;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ImportOrder {

    private String orderId;

    private LocalDateTime occurrenceDate;

    private String store;

    private ImportOrderStatus status;

    private String originSystem;

    private String pickup;

    private List<OrderItem> items;

    public void updateOccurrenceDate(LocalDateTime occurrenceDate) {
        this.occurrenceDate = occurrenceDate;
    }

    public ImportOrder updateStatus(ImportOrderStatus status) {
        this.status = status;
        return this;
    }
    
}

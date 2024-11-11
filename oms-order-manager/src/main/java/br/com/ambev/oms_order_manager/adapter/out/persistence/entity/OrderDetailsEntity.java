package br.com.ambev.oms_order_manager.adapter.out.persistence.entity;

import java.time.LocalDateTime;

import br.com.ambev.oms_order_manager.domain.model.OrderDetails;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "order_details")
@Builder(access = AccessLevel.PRIVATE)
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailsEntity {

    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "occurrence_date")
    private LocalDateTime occurrenceDate;

    @Column(name = "store")
    private String store;

    @Column(name = "status")
    private String status;

    @Column(name = "origin_system")
    private String originSystem;

    @Column(name = "pickup")
    private String pickup;

    public OrderDetails toDomain(){
        return OrderDetails.builder()
                .id(this.id)
                .occurrenceDate(this.occurrenceDate)
                .store(this.store)
                .status(this.status)
                .originSystem(this.originSystem)
                .pickup(this.pickup)
                .build();
    }

    public static OrderDetailsEntity fromDomain(OrderDetails order){
        return OrderDetailsEntity.builder()
                .id(order.getId())
                .occurrenceDate(order.getOccurrenceDate())
                .store(order.getStore())
                .status(order.getStatus())
                .originSystem(order.getOriginSystem())
                .pickup(order.getPickup())
                .build();
    }
    
}

package br.com.ambev.oms_order_import.adapter.out.persistence.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import br.com.ambev.oms_order_import.domain.model.ImportOrder;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "import_order")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ImportOrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_id", nullable = false, length = 50)
    private String orderId;

    @Column(name = "occurrence_date", nullable = false)
    private LocalDateTime occurrenceDate;

    @Column(name = "import_status", nullable = false, length = 50)
    private String importStatus;

    @Column(name = "import_attempts", nullable = false)
    private Integer importAttempts;

    @Column(name = "request_body", nullable = false)
    private String requestBody;

    public static ImportOrderEntity fromDomain(ImportOrder importOrder, Integer importAttempts) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        return ImportOrderEntity.builder()
                .orderId(importOrder.getOrderId())
                .occurrenceDate(importOrder.getOccurrenceDate())
                .importStatus(importOrder.getStatus().toString())
                .importAttempts(importAttempts)
                .requestBody(objectMapper.writeValueAsString(importOrder))
                .build();
    }

    public ImportOrder toDomain() {
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            return objectMapper.readValue(requestBody, ImportOrder.class);
        } catch (JsonProcessingException e) {
            return null;
        }
        
    }
}

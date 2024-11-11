package br.com.ambev.oms_order_import.adapter.out.persistence.entity;

import java.time.LocalDateTime;

public class ImportOrderEntityMock {
    public static ImportOrderEntity create() {
        return ImportOrderEntity.builder()
            .orderId("1")
            .occurrenceDate(LocalDateTime.now())
            .importStatus("SENDED")
            .importAttempts(1)
            .requestBody("requestBody")
            .build();
    }
}

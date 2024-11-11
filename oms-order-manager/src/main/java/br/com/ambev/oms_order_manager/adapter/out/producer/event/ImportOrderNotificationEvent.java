package br.com.ambev.oms_order_manager.adapter.out.producer.event;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.ambev.oms_order_manager.adapter.in.consumer.event.ImportOrderEvent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ImportOrderNotificationEvent implements Serializable {
    
    @JsonProperty("order_id")
    private String orderId;

    @JsonProperty("message")
    private String message;

    @JsonProperty("json_imported_object")
    private ImportOrderEvent jsonImportedObject;

    public static ImportOrderNotificationEvent fromDomain(String message, ImportOrderEvent object) {
        return ImportOrderNotificationEvent.builder()
                .orderId(object.getOrderId())
                .message(message)
                .jsonImportedObject(object)
                .build();
    }
}

package br.com.ambev.oms_order_import.adapter.in.rest;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import br.com.ambev.oms_order_import.adapter.in.rest.request.ImportOrderRequest;
import br.com.ambev.oms_order_import.port.in.OrderUC;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequestMapping("/order")
@RestController
@RequiredArgsConstructor
public class OmsOrderController {

    private final OrderUC orderUC;

    @Operation(summary = "Importação de novo pedido")
    @PostMapping(path = "/import", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> importOrder(@RequestBody ImportOrderRequest request) throws JsonProcessingException {

        log.info("Starting import order process. ID {}", request.getOrderId());
        orderUC.importOrder(request.toDomain());
        
        log.info("Finished import order process. ID {}", request.getOrderId());
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).build();
    }
    
}

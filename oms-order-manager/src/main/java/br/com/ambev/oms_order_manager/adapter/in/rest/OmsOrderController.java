package br.com.ambev.oms_order_manager.adapter.in.rest;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ambev.oms_order_manager.adapter.in.rest.response.DefaultResponse;
import br.com.ambev.oms_order_manager.adapter.in.rest.response.OrderResponse;
import br.com.ambev.oms_order_manager.domain.model.OrderDetails;
import br.com.ambev.oms_order_manager.port.in.OrderUC;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequestMapping("/order")
@RestController
@RequiredArgsConstructor
public class OmsOrderController {

    private final OrderUC orderUC;

    @Operation(summary = "Busca um pedido pelo orderId")
    @GetMapping(path = "/{orderId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DefaultResponse<OrderResponse>> getOrder(
        @PathVariable(name = "orderId", required = true) final String orderId) {

        log.info("Starting find order. ID {}", orderId);
        OrderDetails order = orderUC.findOrderById(orderId);

        log.info("Order found. ID {}", orderId);
        return ResponseEntity.ok(DefaultResponse.<OrderResponse>builder()
            .httpStatus(200)
            .resultData(OrderResponse.createFromDomain(order))
            .build());
    }   
}

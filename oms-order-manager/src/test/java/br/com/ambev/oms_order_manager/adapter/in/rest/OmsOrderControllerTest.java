package br.com.ambev.oms_order_manager.adapter.in.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import br.com.ambev.oms_order_manager.adapter.in.rest.response.DefaultResponse;
import br.com.ambev.oms_order_manager.adapter.in.rest.response.OrderResponse;
import br.com.ambev.oms_order_manager.domain.model.OrderDetailsMock;
import br.com.ambev.oms_order_manager.port.in.OrderUC;

@ExtendWith(MockitoExtension.class)
class OmsOrderControllerTest {

    @Mock
    private OrderUC orderUC;

    @InjectMocks
    private OmsOrderController omsOrderController;

    @Test
    @DisplayName("Deve obter o pedido, metodo getOrder")
    void shouldGetOrder(){

        String id = "1";
        when(orderUC.findOrderById(id)).thenReturn(OrderDetailsMock.create(id));

        ResponseEntity<DefaultResponse<OrderResponse>> response = omsOrderController.getOrder(id);

        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        assertEquals(id, response.getBody().getResultData().getId());
        
    }
    
}

package br.com.ambev.oms_order_import.adapter.in.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.JsonProcessingException;

import br.com.ambev.oms_order_import.adapter.in.rest.request.ImportOrderRequestMock;
import br.com.ambev.oms_order_import.port.in.OrderUC;

@ExtendWith(MockitoExtension.class)
class OmsOrderControllerTest {

    @Mock
    private OrderUC orderUC;

    @InjectMocks
    private OmsOrderController omsOrderController;

    @Test
    @DisplayName("Deve importar o pedido, metodo importOrder")
    void shouldImportOrder() throws JsonProcessingException{

        doNothing().when(orderUC).importOrder(any());

        ResponseEntity<Void> response = omsOrderController.importOrder(ImportOrderRequestMock.create());

        assertNotNull(response);
        assertEquals(201, response.getStatusCode().value());
        
    }
    
}

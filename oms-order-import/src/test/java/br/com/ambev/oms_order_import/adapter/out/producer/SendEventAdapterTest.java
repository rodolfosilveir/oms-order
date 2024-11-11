package br.com.ambev.oms_order_import.adapter.out.producer;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import br.com.ambev.oms_order_import.domain.model.ImportOrder;
import br.com.ambev.oms_order_import.domain.model.ImportOrderMock;
import br.com.ambev.oms_order_import.port.out.KafkaProducerPort;

@ExtendWith(MockitoExtension.class)
class SendEventAdapterTest {

    @Mock
    private KafkaProducerPort kafkaProducerPort;

    @InjectMocks
    private SendEventAdapter sendEventAdapter;

    @Test
    @DisplayName("Deve enviar o pedido, metodo sendImportOrder")
    void shoulSendImportOrder(){

        ReflectionTestUtils.setField(sendEventAdapter, "orderImportTopic", "topic");

        ImportOrder order = ImportOrderMock.create();

        doNothing().when(kafkaProducerPort).sendEvent(anyString(), any());

        sendEventAdapter.sendImportOrder(order);

        verify(kafkaProducerPort, times(1)).sendEvent(anyString(), any());
    }
    
}

package br.com.ambev.oms_order_import.adapter.out.producer;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.Serializable;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;

@ExtendWith(MockitoExtension.class)
class KafkaProducerTest {

    @Mock
    private KafkaTemplate<String, Serializable> kafkaTemplate;

    @InjectMocks
    private KafkaProducer kafkaProducer;

    @Test
    @DisplayName("Deve enviar o evento, metodo sendEvent")
    void shoulSendEvent(){

        when(kafkaTemplate.send("topic", "message")).thenReturn(null);

        kafkaProducer.sendEvent("topic", "message");

        verify(kafkaTemplate, times(1)).send("topic", "message");
    }
    
}

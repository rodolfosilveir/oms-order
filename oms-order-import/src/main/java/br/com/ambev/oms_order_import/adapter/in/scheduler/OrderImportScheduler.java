package br.com.ambev.oms_order_import.adapter.in.scheduler;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;

import br.com.ambev.oms_order_import.port.in.OrderUC;
import lombok.RequiredArgsConstructor;

@Component
@EnableScheduling
@RequiredArgsConstructor
public class OrderImportScheduler {

    private final OrderUC orderUC;

    @Scheduled(fixedDelayString = "${schedules.order-sended.verification.delay}000")
    public void retrySendedOrders() throws JsonProcessingException {
        orderUC.retrySendedOrders();
    }
    
}

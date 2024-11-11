package br.com.ambev.oms_order_import.adapter.in.rest.request;

import java.util.Collections;

public class ImportOrderRequestMock {
    
    public static ImportOrderRequest create(){
        return ImportOrderRequest.builder()
            .orderId("1")
            .store("store")
            .status("status")
            .originSystem("originSystem")
            .pickup("pickup")
            .items(Collections.emptyList())
            .build();
    }
}

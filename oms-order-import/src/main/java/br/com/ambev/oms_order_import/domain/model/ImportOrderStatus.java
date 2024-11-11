package br.com.ambev.oms_order_import.domain.model;

import lombok.Generated;

@Generated
public enum ImportOrderStatus {
    SENDED("SENDED"),
    IMPORTED("IMPORTED");

    private String status;

    ImportOrderStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public static ImportOrderStatus fromText(String text){
        for (ImportOrderStatus status: ImportOrderStatus.values()) {
            if (status.getStatus().equalsIgnoreCase(text.toUpperCase())) {
                return status;
            }
        }

        return null;
    }
}

package br.com.ambev.oms_order_import.domain.exception;

import lombok.Generated;

@Generated
public class EventSendException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public EventSendException(String message) {
        super(message);
    }

    public EventSendException(String message, Throwable cause) {
        super(message, cause);
    }
    
}

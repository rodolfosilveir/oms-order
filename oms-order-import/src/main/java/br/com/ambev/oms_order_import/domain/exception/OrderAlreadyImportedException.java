package br.com.ambev.oms_order_import.domain.exception;

public class OrderAlreadyImportedException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public OrderAlreadyImportedException(String orderId) {
        super("Order already imported: " + orderId);
    }

}

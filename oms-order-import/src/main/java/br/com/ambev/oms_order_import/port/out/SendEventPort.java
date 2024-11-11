package br.com.ambev.oms_order_import.port.out;

import br.com.ambev.oms_order_import.domain.model.ImportOrder;

public interface SendEventPort {

    void sendImportOrder(ImportOrder event);
}

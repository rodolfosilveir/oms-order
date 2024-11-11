package br.com.ambev.oms_order_manager.adapter.out.persistence;

import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.ambev.oms_order_manager.adapter.out.persistence.entity.OrderDetailsEntity;
import br.com.ambev.oms_order_manager.adapter.out.persistence.repository.OrderDetailsRepository;
import br.com.ambev.oms_order_manager.domain.model.OrderDetails;
import br.com.ambev.oms_order_manager.port.out.OrderDetailsPort;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class OrderDetailsAdapter implements OrderDetailsPort{

    private final OrderDetailsRepository orderDetailsRepository;

    @Override
    public Optional<OrderDetails> findOrderById(String id) {        
        return orderDetailsRepository.findById(id)
            .map(OrderDetailsEntity::toDomain);        
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveOrder(OrderDetails order) {
        orderDetailsRepository.save(OrderDetailsEntity.fromDomain(order));
    }

}

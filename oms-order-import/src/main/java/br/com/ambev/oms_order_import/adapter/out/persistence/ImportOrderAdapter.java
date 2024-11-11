package br.com.ambev.oms_order_import.adapter.out.persistence;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;

import br.com.ambev.oms_order_import.adapter.out.persistence.entity.ImportOrderEntity;
import br.com.ambev.oms_order_import.adapter.out.persistence.repository.ImportOrderRepository;
import br.com.ambev.oms_order_import.domain.model.ImportOrder;
import br.com.ambev.oms_order_import.port.out.ImportOrderPort;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ImportOrderAdapter implements ImportOrderPort {

    private final ImportOrderRepository importOrderRepository;
    
    @Override
    public void create(ImportOrder importOrder) throws JsonProcessingException {
        importOrderRepository.saveAndFlush(ImportOrderEntity.fromDomain(importOrder, 1));
    }

    @Override
    public List<ImportOrder> findByOrderId(String orderId) {
        List<ImportOrderEntity> entities = importOrderRepository.findByOrderId(orderId);
        return entities.stream()
            .map(ImportOrderEntity::toDomain)
            .filter(Objects::nonNull) 
            .collect(Collectors.toList());
    }

    @Override
    public void updateStatus(ImportOrder importOrder) {
        importOrderRepository.updateOrderStatus(importOrder.getOrderId(), importOrder.getStatus().toString());
    }

    @Override
    public List<ImportOrder> findSendedOrders() {
        List<ImportOrderEntity> entities = importOrderRepository.findSendedOrders();
        return entities.stream()
            .map(ImportOrderEntity::toDomain)
            .filter(Objects::nonNull) 
            .collect(Collectors.toList());
    }

    @Override
    public void incrementAttempts(ImportOrder importOrder) throws JsonProcessingException {
        Optional<Integer> attempts = importOrderRepository.findAttemptByOrderId(importOrder.getOrderId());
        importOrderRepository.saveAndFlush(ImportOrderEntity.fromDomain(importOrder, attempts.isPresent()?attempts.get()+1:1));
    }

    
}

package br.com.ambev.oms_order_manager.adapter.out.persistence;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.ambev.oms_order_manager.adapter.out.persistence.entity.OrderItemEntity;
import br.com.ambev.oms_order_manager.adapter.out.persistence.entity.OrderItemProjection;
import br.com.ambev.oms_order_manager.adapter.out.persistence.repository.OrderItemRepository;
import br.com.ambev.oms_order_manager.domain.model.Item;
import br.com.ambev.oms_order_manager.domain.model.OrderItem;
import br.com.ambev.oms_order_manager.port.out.OrderItemPort;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class OrderItemAdapter implements OrderItemPort {

    private final OrderItemRepository orderItemRepository;

    @Override
    public List<Item> findItemsByOrderIdWithQuantity(String orderId) {
        return this.mapToItems(orderItemRepository.findItemsByOrderIdWithQuantity(orderId));
    }

    private List<Item> mapToItems(List<OrderItemProjection> orderItemProjections) {
        return orderItemProjections.stream()
                .map(orderItemProjection -> Item.builder()
                        .id(orderItemProjection.getItemId())
                        .name(orderItemProjection.getName())
                        .description(orderItemProjection.getDescription())
                        .category(orderItemProjection.getCategory())
                        .volumeMililiters(orderItemProjection.getVolumeMililiters())
                        .price(orderItemProjection.getPrice())
                        .quantity(orderItemProjection.getQuantity())
                        .build())
                .toList();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveItems(String orderId, List<OrderItem> orderItems) {
        orderItemRepository.saveAll(orderItems.stream()
                .map(orderItem -> OrderItemEntity.fromDomain(orderId, orderItem))
                .collect(Collectors.toList()));
    }
}

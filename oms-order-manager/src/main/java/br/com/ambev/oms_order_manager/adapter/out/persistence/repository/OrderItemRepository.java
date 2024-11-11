package br.com.ambev.oms_order_manager.adapter.out.persistence.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.ambev.oms_order_manager.adapter.out.persistence.entity.OrderItemEntity;
import br.com.ambev.oms_order_manager.adapter.out.persistence.entity.OrderItemProjection;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItemEntity, String> {

    @Query(value = "SELECT i.id AS itemId, i.name AS name, i.description AS description, i.category AS category, " +
                   "i.volume_mililiters AS volumeMililiters, i.price AS price, oi.quantity AS quantity " +
                   "FROM item i " +
                   "JOIN order_item oi ON i.id = oi.item_id " +
                   "WHERE oi.id = :orderId", nativeQuery = true)
    List<OrderItemProjection> findItemsByOrderIdWithQuantity(@Param("orderId") String orderId);
}


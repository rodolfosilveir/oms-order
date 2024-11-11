package br.com.ambev.oms_order_manager.adapter.out.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.ambev.oms_order_manager.adapter.out.persistence.entity.OrderDetailsEntity;

@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetailsEntity, String> {

    @Query(value = "SELECT * FROM \"order\" where id = :id", nativeQuery = true)
    Optional<OrderDetailsEntity> findOrderById(@Param("id") String id);
    
}

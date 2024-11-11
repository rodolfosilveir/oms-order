package br.com.ambev.oms_order_import.adapter.out.persistence.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.ambev.oms_order_import.adapter.out.persistence.entity.ImportOrderEntity;
import jakarta.transaction.Transactional;

@Repository
public interface ImportOrderRepository  extends JpaRepository<ImportOrderEntity, String> {

    List<ImportOrderEntity> findByOrderId(String orderId);

    @Query(value = "SELECT import_attempts FROM public.import_order WHERE order_id = :orderId", nativeQuery = true)
    Optional<Integer> findAttemptByOrderId(@Param("orderId") String orderId);

    @Query(value = "SELECT * FROM import_order WHERE import_status = 'SENDED' AND import_attempts <= 3 AND occurrence_date <= NOW() - INTERVAL '3 minutes'", nativeQuery = true)
    List<ImportOrderEntity> findSendedOrders();

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE import_order SET import_status = :status WHERE order_id = :orderId", nativeQuery = true)
    void updateOrderStatus(@Param("orderId") String orderId, @Param("status") String status);
    
}

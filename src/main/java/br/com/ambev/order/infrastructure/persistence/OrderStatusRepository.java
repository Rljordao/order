package br.com.ambev.order.infrastructure.persistence;


import br.com.ambev.order.domain.model.OrderStatus;
import br.com.ambev.order.domain.model.enums.OrderStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderStatusRepository extends JpaRepository<OrderStatus, Long> {

   Optional<OrderStatus> findByStatus(OrderStatusEnum status);
}

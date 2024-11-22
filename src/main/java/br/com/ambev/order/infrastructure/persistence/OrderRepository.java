package br.com.ambev.order.infrastructure.persistence;

import br.com.ambev.order.domain.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findByOrderNumber(BigInteger orderNumber);
    boolean existsByOrderNumber(BigInteger orderNumber);
}

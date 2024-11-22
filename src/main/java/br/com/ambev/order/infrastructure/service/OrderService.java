package br.com.ambev.order.infrastructure.service;

import br.com.ambev.order.application.gateway.OrderGateway;
import br.com.ambev.order.application.gateway.OrderStatusGateway;
import br.com.ambev.order.domain.exception.OrderNotFoundException;
import br.com.ambev.order.domain.model.Order;
import br.com.ambev.order.domain.model.enums.OrderStatusEnum;
import br.com.ambev.order.infrastructure.persistence.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
@RequiredArgsConstructor
public class OrderService implements OrderGateway {

    private final OrderRepository orderRepository;
    private final OrderStatusGateway orderStatusService;

    @Override
    public Order fetchOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(
                        String.format("Pedido com ID %d não encontrado", id)
                ));
    }

    @Override
    @Transactional
    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public void deleteOrder(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(
                        String.format("Pedido com ID %d não encontrado", id)
                ));
        setOrderStatus(order, OrderStatusEnum.CANCELLED);
        orderRepository.save(order);
    }

    @Override
    public Boolean existsByOrderNumber(BigInteger orderNumber) {
        return orderRepository.existsByOrderNumber(orderNumber);
    }
    @Override
    public Order findOrderByOrderNumber(BigInteger orderNumber) {
        return orderRepository.findByOrderNumber(orderNumber)
                .orElseThrow(() -> new OrderNotFoundException(
                        String.format("Pedido com número %d não encontrado", orderNumber)
                ));
    }

    @Override
    public void setOrderStatus(Order order, OrderStatusEnum statusEnum) {
        var newStatus = orderStatusService.getStatus(statusEnum);
        order.setStatus(newStatus);
    }

}

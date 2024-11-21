package br.com.ambev.order.infrastructure.service;

import br.com.ambev.order.application.gateway.OrderStatusGateway;
import br.com.ambev.order.domain.exception.OrderStatusNotFoundException;
import br.com.ambev.order.domain.model.OrderStatus;
import br.com.ambev.order.domain.model.enums.OrderStatusEnum;
import br.com.ambev.order.infrastructure.persistence.OrderStatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderStatusService implements OrderStatusGateway {

    private final OrderStatusRepository orderStatusRepository;

    public OrderStatus getStatus(OrderStatusEnum status){
        return orderStatusRepository.findByStatus(status)
                .orElseThrow(() -> new OrderStatusNotFoundException(status.name()));
    }
}
